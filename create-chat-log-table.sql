-- Create chat_log table for chat statistics
IF NOT EXISTS (SELECT *
FROM sys.tables
WHERE name = 'chat_log')
BEGIN
    CREATE TABLE chat_log
    (
        ma_chat_log INT PRIMARY KEY IDENTITY(1,1),
        user_email NVARCHAR(255),
        user_message NVARCHAR(MAX),
        bot_reply NVARCHAR(MAX),
        status NVARCHAR(50),
        -- SUCCESS, FAILED, FALLBACK
        created_at DATETIME DEFAULT GETDATE()
    );

    CREATE INDEX idx_chat_log_email ON chat_log(user_email);
    CREATE INDEX idx_chat_log_date ON chat_log(created_at);
END
GO
