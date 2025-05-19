-- tblclient
INSERT INTO tblclient VALUES
('CL001', 'Nguyen Van A', 'Hanoi', '123456789', 'CCCD', '0901234567', 'vana@gmail.com'),
('CL002', 'Tran Thi B', 'Da Nang', '234567891', 'CCCD', '0912345678', 'thib@gmail.com'),
('CL003', 'Le Van C', 'Ho Chi Minh', '345678912', 'Passport', '0923456789', 'vanc@gmail.com'),
('CL004', 'Pham Thi D', 'Can Tho', '456789123', 'CCCD', '0934567891', 'thid@gmail.com'),
('CL005', 'Hoang Van E', 'Hai Phong', '567891234', 'Passport', '0945678912', 'vane@gmail.com');

-- tbltour
INSERT INTO tbltour VALUES
('T001', 'Tour Ha Long', 'Hanoi', 'Tham quan vịnh Hạ Long', 'Quang Ninh'),
('T002', 'Tour Da Lat', 'Ho Chi Minh', 'Khám phá thành phố ngàn hoa', 'Lam Dong'),
('T003', 'Tour Hue', 'Da Nang', 'Di sản văn hóa thế giới', 'Thua Thien Hue'),
('T004', 'Tour Phu Quoc', 'Can Tho', 'Biển xanh cát trắng', 'Kien Giang'),
('T005', 'Tour Sapa', 'Hanoi', 'Khám phá núi rừng Tây Bắc', 'Lao Cai');

-- tbltourdeparture
INSERT INTO tbltourdeparture VALUES
(1, '2025-06-01', '2025-06-05', 'T001'),
(2, '2025-06-10', '2025-06-14', 'T002'),
(3, '2025-06-15', '2025-06-19', 'T003'),
(4, '2025-06-20', '2025-06-25', 'T004'),
(5, '2025-06-26', '2025-06-30', 'T005');

-- tblpriceticket
INSERT INTO tblpriceticket VALUES
(1, 1, 5, 2000000, 1),
(2, 6, 10, 1800000, 1),
(3, 1, 5, 2500000, 2),
(4, 6, 10, 2300000, 2),
(5, 1, 5, 2100000, 3);

-- tbluser
INSERT INTO tbluser VALUES
('U001', 'admin', 'Admin User', 'admin@tour.vn', 'admin123', 'ADMIN'),
('U002', 'user1', 'Nguyen Thi A', 'a@gmail.com', 'pass123', 'USER'),
('U003', 'user2', 'Tran Van B', 'b@gmail.com', 'pass456', 'USER');

-- tblbill
INSERT INTO tblbill VALUES
('B001', '2025-05-01', 'Tour Ha Long - 5 ngày', 'U002', 'CL001', 3, 6000000),
('B002', '2025-05-02', 'Tour Da Lat - 5 ngày', 'U002', 'CL002', 2, 4600000),
('B003', '2025-05-03', 'Tour Hue - 5 ngày', 'U003', 'CL003', 4, 8400000),
('B004', '2025-05-04', 'Tour Sapa - 5 ngày', 'U003', 'CL004', 1, 2100000);

-- tblpenaltybill
INSERT INTO tblpenaltybill VALUES
('PB001', '2025-05-10', 3000000, 'B002'),
('PB002', '2025-05-12', 2100000, 'B004');

-- tblticket
INSERT INTO tblticket VALUES
('TK001', '2025-05-01', 'Khách lớn tuổi', 1, 'B001'),
('TK002', '2025-05-01', '', 1, 'B001'),
('TK003', '2025-05-01', 'Trẻ em', 1, 'B001'),
('TK004', '2025-05-02', '', 2, 'B002'),
('TK005', '2025-05-03', '', 3, 'B003'),
('TK006', '2025-05-03', '', 3, 'B003'),
('TK007', '2025-05-03', '', 3, 'B003'),
('TK008', '2025-05-03', '', 3, 'B003'),
('TK009', '2025-05-04', '', 5, 'B004');
