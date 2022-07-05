DROP DATABASE FootballBookingManagement1

CREATE DATABASE FootballBookingManagement1
USE FootballBookingManagement1


CREATE TABLE tblRoles
(
	roleID varchar(2) PRIMARY KEY,
	roleName nvarchar(50) NOT NULL,
	[status] [bit] default '1'
)

CREATE TABLE tblDistrict
(
	districtId varchar(10) PRIMARY KEY,
	districtName nvarchar(100) NOT NULL,
	[status] [bit] default '1'
)

CREATE TABLE tblUsers
(
	userId varchar(10) PRIMARY KEY,
	fullName nvarchar(50) NOT NULL,
	[address] nvarchar(100) NULL,
	districtId varchar(10) FOREIGN KEY REFERENCES tblDistrict(districtId),
	birthday date NULL,
	phone varchar(11) NULL,
	email varchar(30) NOT NULL,
	accName nvarchar(50) NOT NULL,
	[password] nvarchar(50) NOT NULL,
	roleId varchar(2) FOREIGN KEY REFERENCES tblRoles(roleId),
	[status] [bit] default '1'
)

CREATE TABLE tblFieldCategory
(
	categoryFieldId varchar(10) PRIMARY KEY,
	categoryFieldName nvarchar(100) NOT NULL,
	[status] [bit] default '1'
)

CREATE TABLE tblLocation
(
	locationId varchar(10) PRIMARY KEY,
	locationName nvarchar(100) NOT NULL,
	[status] [bit] default '1'
)

CREATE TABLE tblFields
(
	fieldId varchar(10) PRIMARY KEY,
	fieldName nvarchar(100) NOT NULL,
	[description] nvarchar(500),
	[image] [nvarchar](max) NOT NULL,
	categoryFieldId varchar(10) FOREIGN KEY REFERENCES tblFieldCategory(categoryFieldId),
	price money NOT NULL,
	userId varchar(10) FOREIGN KEY REFERENCES tblUsers(userId),
	locationId varchar(10) FOREIGN KEY REFERENCES tblLocation(locationId),
	districtId varchar(10) FOREIGN KEY REFERENCES tblDistrict(districtId),
	[status] [bit] default '0'
)

CREATE TABLE tblSlots
(
	slotId varchar(10) PRIMARY KEY,
	timeStart varchar(10) NOT NULL,
	timeEnd varchar(10) NOT NULL,
	[status] [bit] default '1'
)

CREATE TABLE tblSlotDetail
(
	slotDetailId varchar(10) PRIMARY KEY,
	slotId varchar(10) FOREIGN KEY REFERENCES tblSlots(slotId),
	fieldId varchar(10) FOREIGN KEY REFERENCES tblFields(fieldId),
	[status] [bit] default '1'
)

CREATE TABLE tblFoodCategory
(
	categoryFoodId varchar(10) PRIMARY KEY,
	categoryFoodName nvarchar(50) NOT NULL,
	[status] [bit] default '1'
)

CREATE TABLE tblFoods
(
	foodId varchar(10) PRIMARY KEY,
	foodName nvarchar(100) NOT NULL,
	[image] [nvarchar](max) NOT NULL,
	categoryFoodId varchar(10) FOREIGN KEY REFERENCES tblFoodCategory(categoryFoodId),
	[status] [bit] default '1'
)

CREATE TABLE tblFoodDetail
(
	foodDetailId varchar(10) PRIMARY KEY,
	foodId varchar(10) FOREIGN KEY REFERENCES tblFoods(foodId),
	fieldId varchar(10) FOREIGN KEY REFERENCES tblFields(fieldId),
	price money NOT NULL,
	[status] [bit] default '1'
)

CREATE TABLE tblFeedback
(
	feedbackId varchar(10) PRIMARY KEY,
	title nvarchar(50) NOT NULL,
	content nvarchar(500) NOT NULL,
	userId varchar(10) FOREIGN KEY REFERENCES tblUsers(userId),
	fieldId varchar(10) FOREIGN KEY REFERENCES tblFields(fieldId),
	[status] [bit] default '1'
)

CREATE TABLE tblBooking
(
	bookingId varchar(10) PRIMARY KEY,
	bookingDate date NOT NULL,
	userId varchar(10) FOREIGN KEY REFERENCES tblUsers(userId),
	totalprice money,
	[status] varchar(10) default 'on-going'
)

CREATE TABLE tblBookingDetail
(
	bookingDetailId varchar(10) PRIMARY KEY,
	bookingId varchar(10) FOREIGN KEY REFERENCES tblBooking(bookingId),
	fieldId varchar(10) FOREIGN KEY REFERENCES tblFields(fieldId),
	playDate date,
	slotDetailId varchar(10) FOREIGN KEY REFERENCES tblSlotDetail(slotDetailId),
	fieldPrice money,
	foodDetailId varchar(10) FOREIGN KEY REFERENCES tblFoodDetail(foodDetailId),
	foodPrice money,
	foodQuantity int,
	[status] [bit] default '1'
)
INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('AD', 'Admin', 1)
INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('MA', 'Manager', 1)
INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('US', 'User', 1)

INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI1', N'Quận 1', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI2', N'Quận 3', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI3', N'Quận 12', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI4', N'Tân Bình', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI5', N'Gò Vấp', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI6', N'Tân Phú', 1)

INSERT INTO tblUsers (userID, fullName, [address], districtId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U1', N'Nguyễn Đức Nhân', N'1/1 D1', 'DI1', '2001-06-24', '089x', 'DN1@gmail.com', 'nhannguyen', '1', 'AD', 1)
INSERT INTO tblUsers (userID, fullName, [address], districtId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U2', N'Võ Trung Quân', N'12/30 D2', 'DI1', '2002-11-11', '080x', 'TQ1@gmail.com', 'quanvo', '1', 'MA', 1)
INSERT INTO tblUsers (userID, fullName, [address], districtId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U3', N'Lê Hoàng Phúc', N'5 D3', 'DI2', '2001-12-05', '081x', 'LP1@gmail.com', 'phucle', '1', 'MA', 1)
INSERT INTO tblUsers (userID, fullName, [address], districtId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U4', N'Trần Minh Quân', N'202/10 D4', 'DI3', '1999-10-15', '090x', 'MQ1@gmail.com', 'quantran', '1', 'US', 1)
INSERT INTO tblUsers (userID, fullName, [address], districtId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U5', N'Trần Hữu Phúc', N'303/22 D5', 'DI4', '2003-12-31', '091x', 'HP1@gmail.com', 'phuctran', '1', 'US', 1)

INSERT INTO tblFieldCategory(categoryFieldId, categoryFieldName, [status]) VALUES ('FC1', N'Sân 5', 1)
INSERT INTO tblFieldCategory(categoryFieldId, categoryFieldName, [status]) VALUES ('FC2', N'Sân 7', 1)
INSERT INTO tblFieldCategory(categoryFieldId, categoryFieldName, [status]) VALUES ('FC3', N'Sân 11', 1)

INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO1', N'20/93 TỔ 10, KHU PHỐ 6', 1)
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO2', N'30/2 ĐƯỜNG SỐ 40', 1)
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO3', N'SỐ 70 ĐƯỜNG TTN 02', 1)

INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI1', N'Sân bóng thống nhất', N'Sân bóng ở trong hẻm phía sau trường Đại học Công nghệ thông tin, khu làng đại học Quốc gia, TP. Hồ Chí Minh. Liên hệ chủ sân: Anh Quân'
			, 'https://phuongthanhngoc.com/media/data/tin-tuc/danh-cho-nha-dau-tu/chieu-dai-san-bong-da-2.jpg'
			, 'FC1', '10', 'U2', 'LO1', 'DI1', 1)
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI2', N'Sân bóng Toàn Thắng', ''
			, 'https://topsaigon.vn/upload/data/images/tieu-ngu.jpg'
			, 'FC2', '12', 'U2', 'LO1', 'DI3', 1)
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI3', N'Sân bóng cỏ nhân tạo CR7', N'Sân bóng mini cỏ nhân tạo Tp Hồ Chí Minh. Anh em có nhu cầu liện hệ...'
			, 'https://dabong.online/wp-content/uploads/2019/07/A%CC%89nh-chu%CC%A3p-Ma%CC%80n-hi%CC%80nh-2019-07-25-lu%CC%81c-11.25.34.png'
			, 'FC3', '15', 'U3', 'LO2', 'DI4', 1)
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI4', N'Sân bóng mini 49', N'Là sân 11 người, chuyên dùng để đào tạo và huấn luyện. Mặt cỏ còn mới'
			, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQpkohiUUT73hWZ9awadw4uJy9EfYIbfKquOLgZJu2W8XRFJAv61Lg4wkYxpgudwdMM24&usqp=CAU'
			, 'FC3', '15', 'U3', 'LO3', 'DI1', 1)

INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL1', '00:00', '01:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL2', '01:00', '02:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL3', '02:00', '03:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL4', '03:00', '04:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL5', '04:00', '05:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL6', '05:00', '06:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL7', '06:00', '07:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL8', '07:00', '08:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL9', '08:00', '09:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL10', '09:00', '10:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL11', '10:00', '11:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL12', '11:00', '12:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL13', '12:00', '13:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL14', '13:00', '14:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL15', '14:00', '15:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL16', '15:00', '16:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL17', '16:00', '17:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL18', '17:00', '18:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL19', '18:00', '19:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL20', '19:00', '20:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL21', '20:00', '21:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL22', '21:00', '22:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL23', '22:00', '23:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL24', '23:00', '00:00', 1)

INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD1', 'SL1', 'FI1', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD2', 'SL1', 'FI2', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD3', 'SL1', 'FI3', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD4', 'SL1', 'FI4', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD5', 'SL2', 'FI1', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD6', 'SL2', 'FI2', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD7', 'SL3', 'FI3', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD8', 'SL3', 'FI4', 1)

INSERT INTO tblFoodCategory(categoryFoodId, categoryFoodName, [status]) VALUES ('OC1', N'Bánh mì', 1)
INSERT INTO tblFoodCategory(categoryFoodId, categoryFoodName, [status]) VALUES ('OC2', N'Bánh snack', 1)
INSERT INTO tblFoodCategory(categoryFoodId, categoryFoodName, [status]) VALUES ('OC3', N'Đồ uống', 1)

INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO1', N'Bánh mì pate', 'https://cdn.tgdd.vn/2020/10/content/7-750x600.jpg'
					, 'OC1', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO2', N'Bánh mì thịt nướng', 'https://nghethuat365.com/wp-content/uploads/2021/09/Cach-an-banh-mi-kep-thit-khong-lo-bi-beo.jpg'
					, 'OC1', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO3', N'Bánh snack bí đỏ', 'https://cf.shopee.vn/file/6c2e631ffd968980730f47d3d88a7f2a'
					, 'OC2', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO4', N'Bánh snack mực lăn', 'https://cdn.tgdd.vn/Products/Images/3364/193601/bhx/snack-muc-lan-muoi-ot-poca-goi-37g-201911061537386523.jpg'
					, 'OC2', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO5', N'Nước suối', 'https://hoangquanhangnhat.com/wp-content/uploads/2020/09/n%C6%B0%E1%BB%9Bc-su%E1%BB%91i.png'
					, 'OC3', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO6', N'Nước chanh muối', 'https://toplist.vn/images/800px/nuoc-chanh-muoi-7up-revive-640852.jpg'
					, 'OC3', 1)

INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD1', 'FO1', 'FI1', '20', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD2', 'FO2', 'FI1', '25', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD3', 'FO5', 'FI1', '10', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD4', 'FO1', 'FI2', '24', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD5', 'FO3', 'FI2', '6', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD6', 'FO4', 'FI2', '5', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD7', 'FO6', 'FI2', '15', 1)

INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO1', '2022-01-24', 'U4', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO2', '2022-03-12', 'U5', '12', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO3', '2022-01-24', 'U4', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO4', '2022-01-24', 'U4', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO5', '2022-01-24', 'U4', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO6', '2022-01-24', 'U4', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO7', '2022-01-24', 'U4', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO8', '2022-01-24', 'U4', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO9', '2022-01-24', 'U4', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO10', '2022-01-24', 'U4', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO11', '2022-01-24', 'U4', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO12', '2022-01-24', 'U4', '50', 'On-Going')

INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, foodDetailId, foodPrice, foodQuantity, [status]) VALUES ('BD1', 'BO1', 'FI1', '2022-01-25', 'SD1', '10', 'FD1', '20', '2', 1)
INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, foodDetailId, foodPrice, foodQuantity, [status]) VALUES ('BD2', 'BO2', 'FI2', '2022-03-14', 'SD2', '12', NULL, NULL, NULL, 1)

INSERT INTO tblFeedback(feedbackId, title, content, userId, fieldId, [status]) VALUES ('FB1', N'Sân bóng tốt', N'Sân bóng này được đặt ở nơi thuận tiện cho việc qua lại, sân cỏ tốt, thoáng mát, sạch sẽ.', 'U4', 'FI1', 1)

