CREATE DATABASE FootballBookingManagement
USE FootballBookingManagement

DROP DATABASE FootballBookingManagement

CREATE TABLE tblRoles
(
	roleID varchar(2) PRIMARY KEY,
	roleName nvarchar(50) NOT NULL,
	[status] [bit] default '1'
)

CREATE TABLE tblCity
(
	cityId varchar(10) PRIMARY KEY,
	cityName nvarchar(100) NOT NULL,
	[status] [bit] default '1'
)

CREATE TABLE tblUsers
(
	userId varchar(10) PRIMARY KEY,
	fullName nvarchar(50) NOT NULL,
	[address] nvarchar(100) NULL,
	cityId varchar(10) FOREIGN KEY REFERENCES tblCity(cityId),
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
	[image] [nvarchar](max) NULL,
	categoryFieldId varchar(10) FOREIGN KEY REFERENCES tblFieldCategory(categoryFieldId),
	userId varchar(10) FOREIGN KEY REFERENCES tblUsers(userId),
	locationId varchar(10) FOREIGN KEY REFERENCES tblLocation(locationId),
	cityId varchar(10) FOREIGN KEY REFERENCES tblCity(cityId),
	[status] [bit] default '0'
)

CREATE TABLE tblSlot
(
	slotId varchar(10) PRIMARY KEY,
	timeStart time,
	timeEnd time,
	[status] [bit] default '1'
)

CREATE TABLE tblSlotDetail
(
	slotDetailId varchar(10) PRIMARY KEY,
	slotId varchar(10) FOREIGN KEY REFERENCES tblSlot(slotId),
	fieldId varchar(10) FOREIGN KEY REFERENCES tblFields(fieldId),
	price money,
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
	[image] [nvarchar](max) NULL,
	categoryFoodId varchar(10) FOREIGN KEY REFERENCES tblFoodCategory(categoryFoodId),
	[status] [bit] default '1'
)

CREATE TABLE tblFoodDetail
(
	foodDetailId varchar(10) PRIMARY KEY,
	foodId varchar(10) FOREIGN KEY REFERENCES tblFoods(foodId),
	fieldId varchar(10) FOREIGN KEY REFERENCES tblFields(fieldId),
	price money,
	quantity int,
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
	[status] [bit] default '1'
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
	[status] [bit] default '1',
)

INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('AD', 'Admin', 1)
INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('MA', 'Manager', 1)
INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('US', 'User', 1)

INSERT INTO tblCity (cityId, cityName, [status]) VALUES ('CT1', N'Hồ Chí Minh', 1)
INSERT INTO tblCity (cityId, cityName, [status]) VALUES ('CT2', N'Hà Nội', 1)
INSERT INTO tblCity (cityId, cityName, [status]) VALUES ('CT3', N'Đà Nẵng', 1)
INSERT INTO tblCity (cityId, cityName, [status]) VALUES ('CT4', N'Cần Thơ', 1)
INSERT INTO tblCity (cityId, cityName, [status]) VALUES ('CT5', N'Thủ Đức', 1)

INSERT INTO tblUsers (userID, fullName, [address], cityId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U1', N'Nguyễn Đức Nhân', N'1/1 D1', 'CT1', '2001-06-24', '089x', 'DN1@gmail.com', 'nhannguyen', '1', 'AD', 1)
INSERT INTO tblUsers (userID, fullName, [address], cityId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U2', N'Võ Trung Quân', N'12/30 D2', 'CT1', '2002-11-11', '080x', 'TQ1@gmail.com', 'quanvo', '1', 'MA', 1)
INSERT INTO tblUsers (userID, fullName, [address], cityId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U3', N'Lê Hoàng Phúc', N'5 D3', 'CT2', '2001-12-05', '081x', 'LP1@gmail.com', 'phucle', '1', 'MA', 1)
INSERT INTO tblUsers (userID, fullName, [address], cityId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U4', N'Trần Minh Quân', N'202/10 D4', 'CT3', '1999-10-15', '090x', 'MQ1@gmail.com', 'quantran', '1', 'US', 1)
INSERT INTO tblUsers (userID, fullName, [address], cityId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U5', N'Trần Hữu Phúc', N'303/22 D5', 'CT2', '2003-12-31', '091x', 'HP1@gmail.com', 'phuctran', '1', 'US', 1)

INSERT INTO tblFieldCategory(categoryFieldId, categoryFieldName, [status]) VALUES ('FC1', N'Sân 5', 1)
INSERT INTO tblFieldCategory(categoryFieldId, categoryFieldName, [status]) VALUES ('FC2', N'Sân 7', 1)
INSERT INTO tblFieldCategory(categoryFieldId, categoryFieldName, [status]) VALUES ('FC3', N'Sân 11', 1)

INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO1', N'20/93 TỔ 10, KHU PHỐ 6, P. LINH TRUNG', 1)
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO2', N'30/2 ĐƯỜNG SỐ 40, HIỆP BÌNH CHÁNH', 1)
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO3', N'SỐ 70 ĐƯỜNG TTN 02, P. TÂN THỚI NHẤT, QUẬN 12', 1)

INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, userId, locationId, cityId, [status]) VALUES ('FI1', N'Sân bóng thống nhất', N'Sân bóng ở trong hẻm phía sau trường Đại học Công nghệ thông tin, khu làng đại học Quốc gia, TP. Hồ Chí Minh. Liên hệ chủ sân: Anh Quân'
			, 'https://sporta.s3.ap-southeast-1.amazonaws.com/uploads/production/image/image/123/15400969_220533135055086_6278862748727258901_n.jpg?X-Amz-Expires=600&X-Amz-Date=20220529T164839Z&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIQW3XISBSHKJGJBQ%2F20220529%2Fap-southeast-1%2Fs3%2Faws4_request&X-Amz-SignedHeaders=host&X-Amz-Signature=926157dc3c81ce995cd91349efb0817365aba1b1d5e27381d0a0a5e3cb6b3f77'
			, 'FC1', 'U2', 'LO1', 'CT5', 1)
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, userId, locationId, cityId, [status]) VALUES ('FI2', N'Sân bóng Toàn Thắng', ''
			, 'https://sporta.s3.ap-southeast-1.amazonaws.com/uploads/production/image/image/491/fa655162-d736-437d-adb8-9a5980f92ec5.jpg?X-Amz-Expires=600&X-Amz-Date=20220529T164935Z&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIQW3XISBSHKJGJBQ%2F20220529%2Fap-southeast-1%2Fs3%2Faws4_request&X-Amz-SignedHeaders=host&X-Amz-Signature=522d840a33666c11c89ece42bfaed5b929aefbd529ad2a6aa73520eb80a345f3'
			, 'FC2', 'U2', 'LO1', 'CT5', 1)
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, userId, locationId, cityId, [status]) VALUES ('FI3', N'Sân bóng cỏ nhân tạo CR7', N'Sân bóng mini cỏ nhân tạo Tp Hồ Chí Minh. Anh em có nhu cầu liện hệ...'
			, 'https://dabong.online/wp-content/uploads/2019/07/A%CC%89nh-chu%CC%A3p-Ma%CC%80n-hi%CC%80nh-2019-07-25-lu%CC%81c-11.25.34.png'
			, 'FC3', 'U3', 'LO2', 'CT5', 1)
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, userId, locationId, cityId, [status]) VALUES ('FI4', N'Sân bóng mini 49', N'Là sân 11 người, chuyên dùng để đào tạo và huấn luyện. Mặt cỏ còn mới'
			, 'https://sporta.s3.ap-southeast-1.amazonaws.com/uploads/production/image/image/123/15400969_220533135055086_6278862748727258901_n.jpg?X-Amz-Expires=600&X-Amz-Date=20220529T164839Z&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIQW3XISBSHKJGJBQ%2F20220529%2Fap-southeast-1%2Fs3%2Faws4_request&X-Amz-SignedHeaders=host&X-Amz-Signature=926157dc3c81ce995cd91349efb0817365aba1b1d5e27381d0a0a5e3cb6b3f77'
			, 'FC3', 'U3', 'LO3', 'CT1', 1)

INSERT INTO tblSlot(slotId, timeStart, timeEnd, [status]) VALUES ('SL1', '07:00:00', '08:00:00', 1)
INSERT INTO tblSlot(slotId, timeStart, timeEnd, [status]) VALUES ('SL2', '08:00:00', '09:00:00', 1)
INSERT INTO tblSlot(slotId, timeStart, timeEnd, [status]) VALUES ('SL3', '09:00:00', '10:00:00', 1)

INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, price, [status]) VALUES ('SD1', 'SL1', 'FI1', '10', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, price, [status]) VALUES ('SD2', 'SL1', 'FI2', '12', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, price, [status]) VALUES ('SD3', 'SL1', 'FI3', '15', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, price, [status]) VALUES ('SD4', 'SL1', 'FI4', '20', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, price, [status]) VALUES ('SD5', 'SL2', 'FI1', '10', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, price, [status]) VALUES ('SD6', 'SL2', 'FI2', '12', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, price, [status]) VALUES ('SD7', 'SL3', 'FI3', '15', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, price, [status]) VALUES ('SD8', 'SL3', 'FI4', '20', 1)

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

INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, quantity, [status]) VALUES ('FD1', 'FO1', 'FI1', '20', '10', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, quantity, [status]) VALUES ('FD2', 'FO2', 'FI1', '25', '5', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, quantity, [status]) VALUES ('FD3', 'FO5', 'FI1', '10', '20', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, quantity, [status]) VALUES ('FD4', 'FO1', 'FI2', '24', '8', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, quantity, [status]) VALUES ('FD5', 'FO3', 'FI2', '6', '30', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, quantity, [status]) VALUES ('FD6', 'FO4', 'FI2', '5', '3', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, quantity, [status]) VALUES ('FD7', 'FO6', 'FI2', '15', '15', 1)

INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO1', '2022-01-24', 'U4', '50', 1)
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO2', '2022-03-12', 'U5', '12', 1)

INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, foodDetailId, foodPrice, foodQuantity, [status]) VALUES ('BD1', 'BO1', 'FI1', '2022-01-25', 'SD1', '10', 'FD1', '20', '2', 1)
INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, foodDetailId, foodPrice, foodQuantity, [status]) VALUES ('BD2', 'BO2', 'FI2', '2022-03-14', 'SD2', '12', NULL, NULL, NULL, 1)

INSERT INTO tblFeedback(feedbackId, title, content, userId, fieldId, [status]) VALUES ('FB1', N'Sân bóng tốt', N'Sân bóng này được đặt ở nơi thuận tiện cho việc qua lại, sân cỏ tốt, thoáng mát, sạch sẽ.', 'U4', 'FI1', 1)

