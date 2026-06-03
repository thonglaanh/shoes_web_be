package com.shoes.service;

import com.shoes.dto.LoginRequest;
import com.shoes.dto.RegisterRequest;
import com.shoes.dto.TaiKhoanResponse;
import com.shoes.entity.TaiKhoan;
import com.shoes.repository.TaiKhoanRepository;
import com.shoes.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TaiKhoanResponse register(RegisterRequest req) {
        if (req.getEmail() == null || req.getEmail().isEmpty()) {
            throw new RuntimeException("Email không được để trống");
        }

        if (taiKhoanRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã được sử dụng");
        }

        TaiKhoan tk = new TaiKhoan();
        tk.setTenNguoiDung(req.getTenNguoiDung());
        tk.setMatKhau(passwordEncoder.encode(req.getMatKhau()));
        tk.setLoaiTaiKhoan("USER");
        tk.setTrangThai("ACTIVE");
        tk.setNgayKhoiTao(LocalDateTime.now());
        tk.setNguoiKhoiTao(req.getEmail());
        tk.setNgayCapNhat(LocalDateTime.now());
        tk.setGioiTinh(req.getGioiTinh());
        tk.setSoDienThoai(req.getSoDienThoai());
        tk.setEmail(req.getEmail());

        if (req.getNamSinh() != null && !req.getNamSinh().isEmpty()) {
            try {
                tk.setNamSinh(LocalDateTime.parse(req.getNamSinh()));
            } catch (DateTimeParseException e) {
                tk.setNamSinh(null);
            }
        }

        TaiKhoan saved = taiKhoanRepository.save(tk);
        String token = jwtUtil.generateToken(saved.getMaTaiKhoan(), saved.getEmail());
        return toResponse(saved, token);
    }

    public TaiKhoanResponse login(LoginRequest req) {
        if (req.getEmail() == null || req.getEmail().isEmpty()) {
            throw new RuntimeException("Email không được để trống");
        }

        Optional<TaiKhoan> tkOpt = taiKhoanRepository.findByEmail(req.getEmail());
        if (tkOpt.isEmpty()) {
            throw new RuntimeException("Email không tồn tại");
        }

        TaiKhoan tk = tkOpt.get();

        // Support both plaintext (old data) and encrypted (new data) passwords
        boolean passwordValid = passwordEncoder.matches(req.getMatKhau(), tk.getMatKhau())
                || tk.getMatKhau().equals(req.getMatKhau());

        if (!passwordValid) {
            throw new RuntimeException("Mật khẩu không đúng");
        }

        if (!"ACTIVE".equals(tk.getTrangThai())) {
            throw new RuntimeException("Tài khoản đã bị khóa");
        }

        String token = jwtUtil.generateToken(tk.getMaTaiKhoan(), tk.getEmail());
        return toResponse(tk, token);
    }

    public TaiKhoanResponse getUser(Integer id) {
        TaiKhoan tk = taiKhoanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));
        return toResponse(tk);
    }

    public TaiKhoanResponse updateProfile(Integer userId, TaiKhoan updated) {
        TaiKhoan existing = taiKhoanRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));

        if (updated.getTenNguoiDung() != null)
            existing.setTenNguoiDung(updated.getTenNguoiDung());
        if (updated.getSoDienThoai() != null)
            existing.setSoDienThoai(updated.getSoDienThoai());
        if (updated.getGioiTinh() != null)
            existing.setGioiTinh(updated.getGioiTinh());
        if (updated.getNamSinh() != null)
            existing.setNamSinh(updated.getNamSinh());
        existing.setNgayCapNhat(LocalDateTime.now());

        TaiKhoan saved = taiKhoanRepository.save(existing);
        return toResponse(saved);
    }

    public TaiKhoanResponse changePassword(Integer userId, String oldPassword, String newPassword) {
        TaiKhoan existing = taiKhoanRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));

        if (!passwordEncoder.matches(oldPassword, existing.getMatKhau())) {
            throw new RuntimeException("Mật khẩu cũ không đúng");
        }
        if (newPassword == null || newPassword.length() < 6) {
            throw new RuntimeException("Mật khẩu mới phải có ít nhất 6 ký tự");
        }

        existing.setMatKhau(passwordEncoder.encode(newPassword));
        existing.setNgayCapNhat(LocalDateTime.now());
        TaiKhoan saved = taiKhoanRepository.save(existing);
        return toResponse(saved);
    }

    private TaiKhoanResponse toResponse(TaiKhoan tk) {
        return toResponse(tk, null);
    }

    private TaiKhoanResponse toResponse(TaiKhoan tk, String token) {
        TaiKhoanResponse res = new TaiKhoanResponse();
        res.setMaTaiKhoan(tk.getMaTaiKhoan());
        res.setTenNguoiDung(tk.getTenNguoiDung());
        res.setLoaiTaiKhoan(tk.getLoaiTaiKhoan());
        res.setTrangThai(tk.getTrangThai());
        res.setNgayKhoiTao(tk.getNgayKhoiTao());
        res.setGioiTinh(tk.getGioiTinh());
        res.setSoDienThoai(tk.getSoDienThoai());
        res.setEmail(tk.getEmail());
        res.setNamSinh(tk.getNamSinh());
        res.setToken(token);
        return res;
    }

    private void checkPermission(Integer requesterId, String... allowedRoles) {
        TaiKhoan requester = taiKhoanRepository.findById(requesterId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản người yêu cầu"));
        boolean allowed = false;
        for (String role : allowedRoles) {
            if (role.equals(requester.getLoaiTaiKhoan())) {
                allowed = true;
                break;
            }
        }
        if (!allowed) {
            throw new RuntimeException("Bạn không có quyền thực hiện thao tác này");
        }
    }

    private TaiKhoan getRequester(Integer requesterId) {
        return taiKhoanRepository.findById(requesterId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản người yêu cầu"));
    }

    public List<TaiKhoanResponse> getCustomers(Integer requesterId) {
        checkPermission(requesterId, "ADMIN", "NHANVIEN");
        List<TaiKhoan> customers = taiKhoanRepository.findByLoaiTaiKhoan("USER");
        return customers.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public TaiKhoanResponse updateCustomer(Integer requesterId, Integer customerId, TaiKhoan updated) {
        checkPermission(requesterId, "ADMIN");

        TaiKhoan existing = taiKhoanRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));

        if (!"USER".equals(existing.getLoaiTaiKhoan())) {
            throw new RuntimeException("Tài khoản này không phải khách hàng");
        }

        if (updated.getTenNguoiDung() != null)
            existing.setTenNguoiDung(updated.getTenNguoiDung());
        if (updated.getEmail() != null && !updated.getEmail().equals(existing.getEmail())) {
            if (taiKhoanRepository.findByEmail(updated.getEmail()).isPresent()) {
                throw new RuntimeException("Email đã được sử dụng");
            }
            existing.setEmail(updated.getEmail());
        }
        if (updated.getSoDienThoai() != null)
            existing.setSoDienThoai(updated.getSoDienThoai());
        if (updated.getGioiTinh() != null)
            existing.setGioiTinh(updated.getGioiTinh());
        if (updated.getTrangThai() != null)
            existing.setTrangThai(updated.getTrangThai());
        if (updated.getMatKhau() != null && !updated.getMatKhau().isEmpty()) {
            existing.setMatKhau(updated.getMatKhau());
        }
        existing.setNgayCapNhat(LocalDateTime.now());

        TaiKhoan saved = taiKhoanRepository.save(existing);
        return toResponse(saved);
    }

    /**
     * Khóa / Mở khóa khách hàng — ADMIN only
     */
    public TaiKhoanResponse toggleCustomerStatus(Integer requesterId, Integer customerId) {
        checkPermission(requesterId, "ADMIN");

        TaiKhoan existing = taiKhoanRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));

        if (!"USER".equals(existing.getLoaiTaiKhoan())) {
            throw new RuntimeException("Tài khoản này không phải khách hàng");
        }

        existing.setTrangThai("ACTIVE".equals(existing.getTrangThai()) ? "LOCKED" : "ACTIVE");
        existing.setNgayCapNhat(LocalDateTime.now());

        TaiKhoan saved = taiKhoanRepository.save(existing);
        return toResponse(saved);
    }

    public List<TaiKhoanResponse> getEmployees(Integer requesterId) {
        checkPermission(requesterId, "ADMIN", "NHANVIEN");
        List<TaiKhoan> employees = taiKhoanRepository.findByLoaiTaiKhoanIn(
                List.of("ADMIN", "NHANVIEN"));
        return employees.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<TaiKhoanResponse> getAllAccounts(Integer requesterId) {
        checkPermission(requesterId, "ADMIN");
        return taiKhoanRepository.findAll().stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    public TaiKhoanResponse createEmployee(Integer requesterId, TaiKhoan employee) {
        checkPermission(requesterId, "ADMIN");

        if (employee.getEmail() == null || employee.getEmail().isEmpty()) {
            throw new RuntimeException("Email không được để trống");
        }
        if (taiKhoanRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã được sử dụng");
        }

        if (employee.getLoaiTaiKhoan() == null) {
            employee.setLoaiTaiKhoan("NHANVIEN");
        }
        if (employee.getTrangThai() == null) {
            employee.setTrangThai("ACTIVE");
        }
        employee.setNgayKhoiTao(LocalDateTime.now());
        employee.setNgayCapNhat(LocalDateTime.now());
        TaiKhoan requester = getRequester(requesterId);
        employee.setNguoiKhoiTao(requester.getEmail());

        TaiKhoan saved = taiKhoanRepository.save(employee);
        return toResponse(saved);
    }

    public TaiKhoanResponse updateEmployee(Integer requesterId, Integer employeeId, TaiKhoan updated) {
        checkPermission(requesterId, "ADMIN");

        TaiKhoan existing = taiKhoanRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));

        if (updated.getTenNguoiDung() != null)
            existing.setTenNguoiDung(updated.getTenNguoiDung());
        if (updated.getEmail() != null && !updated.getEmail().equals(existing.getEmail())) {
            if (taiKhoanRepository.findByEmail(updated.getEmail()).isPresent()) {
                throw new RuntimeException("Email đã được sử dụng");
            }
            existing.setEmail(updated.getEmail());
        }
        if (updated.getSoDienThoai() != null)
            existing.setSoDienThoai(updated.getSoDienThoai());
        if (updated.getGioiTinh() != null)
            existing.setGioiTinh(updated.getGioiTinh());
        if (updated.getLoaiTaiKhoan() != null)
            existing.setLoaiTaiKhoan(updated.getLoaiTaiKhoan());
        if (updated.getTrangThai() != null)
            existing.setTrangThai(updated.getTrangThai());
        if (updated.getMatKhau() != null && !updated.getMatKhau().isEmpty()) {
            existing.setMatKhau(updated.getMatKhau());
        }
        existing.setNgayCapNhat(LocalDateTime.now());

        TaiKhoan saved = taiKhoanRepository.save(existing);
        return toResponse(saved);
    }

    public void deleteEmployee(Integer requesterId, Integer employeeId) {
        checkPermission(requesterId, "ADMIN");

        TaiKhoan existing = taiKhoanRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));

        // Không cho xóa chính mình
        if (existing.getMaTaiKhoan().equals(requesterId)) {
            throw new RuntimeException("Không thể xóa tài khoản của chính mình");
        }

        taiKhoanRepository.deleteById(employeeId);
    }
}
