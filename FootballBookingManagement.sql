DROP DATABASE FootballBookingManagement

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
	[status] varchar(10) default 'Request'
)

CREATE TABLE tblLocation
(
	locationId varchar(10) PRIMARY KEY,
	locationName nvarchar(100) NOT NULL,
	[status] varchar(10) default 'Request'
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
	[status] varchar(10) default 'Request'
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
	[status] varchar(10) default 'On-Going'
)

CREATE TABLE tblBookingDetail
(
	bookingDetailId varchar(10) PRIMARY KEY,
	bookingId varchar(10) FOREIGN KEY REFERENCES tblBooking(bookingId),
	fieldId varchar(10) FOREIGN KEY REFERENCES tblFields(fieldId),
	playDate date,
	slotDetailId varchar(10) FOREIGN KEY REFERENCES tblSlotDetail(slotDetailId),
	fieldPrice money,
	[status] [bit] default '1'
)

INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('AD', 'Admin', 1)
INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('MA', 'Manager', 1)
INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('US', 'User', 1)

INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI01', N'Quận 1', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI02', N'Quận 3', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI03', N'Quận 4', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI04', N'Quận 5', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI05', N'Quận 6', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI06', N'Quận 7', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI07', N'Quận 8', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI08', N'Quận 10', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI09', N'Quận 11', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI10', N'Quận 12', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI11', N'Quận Bình Tân', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI12', N'Quận Bình Thạnh', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI13', N'Quận Gò Vấp', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI14', N'Quận Phú Nhuận', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI15', N'Quận Tân Bình', 1)
INSERT INTO tblDistrict (districtId, districtName, [status]) VALUES ('DI16', N'Quận Tân Phú', 1)

INSERT INTO tblUsers (userID, fullName, [address], districtId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U01', N'Nguyễn Đức Nhân', N'1/1 D1', 'DI01', '2001-06-24', '089x', 'DN1@gmail.com', 'nhannguyen', '1', 'AD', 1)
INSERT INTO tblUsers (userID, fullName, [address], districtId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U02', N'Võ Trung Quân', N'12/30 D2', 'DI01', '2002-11-11', '080x', 'TQ1@gmail.com', 'quanvo', '1', 'MA', 1)
INSERT INTO tblUsers (userID, fullName, [address], districtId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U03', N'Lê Hoàng Phúc', N'5 D3', 'DI02', '2001-12-05', '081x', 'LP1@gmail.com', 'phucle', '1', 'MA', 1)
INSERT INTO tblUsers (userID, fullName, [address], districtId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U04', N'Trần Minh Quân', N'202/10 D4', 'DI03', '1999-10-15', '090x', 'MQ1@gmail.com', 'quantran', '1', 'US', 1)
INSERT INTO tblUsers (userID, fullName, [address], districtId, birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U05', N'Trần Hữu Phúc', N'303/22 D5', 'DI04', '2003-12-31', '091x', 'HP1@gmail.com', 'phuctran', '1', 'US', 1)

INSERT INTO tblUsers([userId],[fullName],[address],[districtId],[birthday],[phone],[email],[accName],[password],[roleId],[status])
VALUES('U06', N'Trần Lý Sơn', N'123/6 A1', 'DI07', '2003-12-31', '091x', 'HP1@gmail.com', 'phuctran', '1', 'US', 1)
INSERT INTO [dbo].[tblUsers]([userId],[fullName],[address],[districtId],[birthday],[phone],[email],[accName],[password],[roleId],[status])
VALUES ('U07', N'Hữu Bảo Anh', N'303/22 D5', 'DI08', '2001-10-31', '091x', 'HPYY1@gmail.com', 'baoanh', '1', 'US', 1)
INSERT INTO [dbo].[tblUsers]([userId],[fullName],[address],[districtId],[birthday],[phone],[email],[accName],[password],[roleId],[status])
VALUES	('U08', N'Thế Anh', N'303/22 D5', 'DI09', '1999-12-01', '091x', 'HPYU2@gmail.com', 'theanh', '1', 'MA', 1)
INSERT INTO [dbo].[tblUsers]([userId],[fullName],[address],[districtId],[birthday],[phone],[email],[accName],[password],[roleId],[status])
VALUES	('U09', N'Quốc Anh', N'303/22 D5', 'DI10', '2000-07-31', '091x', 'HPER3@gmail.com', 'quocanh', '1', 'AD', 1)
INSERT INTO [dbo].[tblUsers]([userId],[fullName],[address],[districtId],[birthday],[phone],[email],[accName],[password],[roleId],[status])
VALUES	('U010', N'Chí Bảo', N'303/22 D5', 'DI11', '1997-02-02', '091x', 'HPKL4@gmail.com', 'chibao', '1', 'US', 1)
INSERT INTO [dbo].[tblUsers]([userId],[fullName],[address],[districtId],[birthday],[phone],[email],[accName],[password],[roleId],[status])
VALUES	('U011', N'Bảo Châu', N'303/22 D5', 'DI12', '2003-03-31', '091x', 'HPSD5@gmail.com', 'baochau', '1', 'US', 1)
INSERT INTO [dbo].[tblUsers]([userId],[fullName],[address],[districtId],[birthday],[phone],[email],[accName],[password],[roleId],[status])
VALUES ('U012', N'An Cơ', N'303/22 D5', 'DI13', '2003-11-11', '091x', 'HPPA6@gmail.com', 'anco', '1', 'US', 1)
INSERT INTO [dbo].[tblUsers]([userId],[fullName],[address],[districtId],[birthday],[phone],[email],[accName],[password],[roleId],[status])
VALUES ('U013', N'Tiểu Bảo', N'303/22 D5', 'DI14', '2003-01-01', '091x', 'HPÂ7@gmail.com', 'tieubao', '1', 'US', 1)

INSERT INTO tblFieldCategory(categoryFieldId, categoryFieldName, [status]) VALUES ('FC01', N'Sân 5', 'Active')
INSERT INTO tblFieldCategory(categoryFieldId, categoryFieldName, [status]) VALUES ('FC02', N'Sân 7', 'Active')
INSERT INTO tblFieldCategory(categoryFieldId, categoryFieldName, [status]) VALUES ('FC03', N'Sân 11', 'Active')

INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO01', N'20/93 Tổ 10, Khu phố 6', 'Active')
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO02', N'30/2 Đường số 40', 'Active')
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO03', N'70 Đường TTN 02', 'Active')
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO04', N'961, Đường Nguyễn Ảnh Thủ', 'Active')
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO05', N'311 Đường số 7', 'Active')
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO06', N'31 Lý Chình Thắng', 'Active')
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO07', N'12 Nam Kỳ Khởi Nghĩa', 'Active')
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO08', N'15/6 Xa Lộ Hà Nội', 'Active')
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO09', N'230 Đường Nguyễn Tri Phương', 'Active')
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO10', N'11 Ngô Gia Tự', 'Active')
INSERT INTO tblLocation(locationId, locationName, [status]) VALUES ('LO11', N'3 Đường Hồng Bàng', 'Active')

INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI01', N'Sân bóng thống nhất', N'Sân bóng ở trong hẻm phía sau trường Đại học Công nghệ thông tin, khu làng đại học Quốc gia, TP. Hồ Chí Minh. Liên hệ chủ sân: Anh Quân'
			, 'https://phuongthanhngoc.com/media/data/tin-tuc/danh-cho-nha-dau-tu/chieu-dai-san-bong-da-2.jpg'
			, 'FC01', '10', 'U02', 'LO01', 'DI01', 'Active')
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI02', N'Sân bóng Toàn Thắng', ''
			, 'https://topsaigon.vn/upload/data/images/tieu-ngu.jpg'
			, 'FC02', '12', 'U02', 'LO01', 'DI03', 'Active')
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI03', N'Sân bóng cỏ nhân tạo CR7', N'Sân bóng mini cỏ nhân tạo Tp Hồ Chí Minh. Anh em có nhu cầu liện hệ...'
			, 'https://dabong.online/wp-content/uploads/2019/07/A%CC%89nh-chu%CC%A3p-Ma%CC%80n-hi%CC%80nh-2019-07-25-lu%CC%81c-11.25.34.png'
			, 'FC03', '15', 'U03', 'LO02', 'DI04', 'Active')
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI04', N'Sân bóng mini 49', N'Là sân 11 người, chuyên dùng để đào tạo và huấn luyện. Mặt cỏ còn mới'
			, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQpkohiUUT73hWZ9awadw4uJy9EfYIbfKquOLgZJu2W8XRFJAv61Lg4wkYxpgudwdMM24&usqp=CAU'
			, 'FC03', '15', 'U03', 'LO03', 'DI01', 'Active')
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI05', N'Sân bóng đá Kaka', N'Sân bóng có đầy đủ tiện ích, công trình phụ được đầu tư xây dựng bài bản. Hệ thống đèn chiếu sáng hiện đại, cho ánh sáng chân thực nhất.'
			, 'https://thegioithethao.vn/upload_images/images/2021/04/08/san-bong-san-banh-kaka.jpg'
			, 'FC03', '15', 'U03', 'LO03', 'DI01', 'Active')
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI06', N'Sân bóng đá Cây Sung 379', N'Sân thoáng, rỗng rãi, phù hợp tổ chức giải đấu phong trào, giải nghiêp dư bán chuyên. Có thêm cả khu vực nước uống, cafe giải khát và bàn bida.'
			, 'https://thegioithethao.vn/upload_images/images/2021/04/08/san-bong-da-cay-sung-379.jpg'
			, 'FC03', '15', 'U03', 'LO02', 'DI04', 'Active')
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI07', N'Sân bóng King Sport', N'Sân được đầu tư bài bản hệ thống lưới, đèn chiếu sáng, công trình phụ trợ,... đảm bảo những điều kiện thi đấu tốt nhất cho khách hàng.'
			, 'https://www.baosoctrang.org.vn/uploads/image/2019/10/08/Trong%20tai.jpg'
			, 'FC03', '15', 'U02', 'LO01', 'DI03', 'Active')
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI08', N'Sân bóng Nam Sài Gòn', N'Sân Nam Sài Gòn với quy mô sân lớn, hiện đại, đầy đủ tiện ích nên sân được thường xuyên diễn ra các trận đấu lớn, các cầu thủ cũng lựa chọn sân là nơi tập luyện.'
			, 'https://thegioiconhantao.com.vn/wp-content/uploads/2016/11/koloban.jpg'
			, 'FC03', '15', 'U02', 'LO04', 'DI05', 'Active')
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI09', N'Sân bóng đá 77', N'Với hệ thống đèn chiếu sáng chân thực, chất lượng sân rất tốt từ mặt cỏ nhân tạo vẫn còn mới và sạch sẽ, không trơn trượt.'
			, 'https://oct.vn/upload/files/he%20thong%20san%20the%20thao/San%20bong%20da%20mini%20co%20nhan%20tao.jpg'
			, 'FC03', '15', 'U03', 'LO03', 'DI01', 'Active')
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI10', N'Sân bóng đá Mini Khu Phố 7', N'Nơi đây là sân bóng được đưa vào sử dụng chưa lâu nên vẫn còn khá mới, bề mặt sân cỏ rất tốt với độ đồng đều cao.'
			, 'https://i.pinimg.com/originals/7b/b7/08/7bb708aaa69df278be4bbf04c72bb06f.jpg'
			, 'FC03', '15', 'U03', 'LO03', 'DI01', 'Active')
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI11', N'Sân bóng đá Đại Nam WeSport', N'Sân Đại Nam là địa điểm lý tưởng để tổ chức các giải bóng đá mini tại khu vực, với số lượng sân lớn, nhiều lựa chọn sân, chất lượng cỏ vẫn rất tốt, chắc chắn các cầu thủ sẽ có những giờ phút thi đấu tuyệt vời tại sân.'
			, 'https://xaydung.edu.vn/wp-content/uploads/san-bong-co-nhan-tao-cho-7-nguoi.jpg'
			, 'FC03', '15', 'U03', 'LO03', 'DI01', 'Active')
INSERT INTO tblFields(fieldId, fieldName, [description], [image], categoryFieldId, price, userId, locationId, districtId, [status]) VALUES ('FI12', N'Sân bóng đá Siêu Sao', N'Sân bóng đã hoạt động nhiều năm và là một trong những điểm đến yêu thích của cộng đồng bóng đá phong trào.'
			, 'https://static.tapchitaichinh.vn/w800/images/upload/hongvan/dau-tu-san-co-nhan-tao-22.jpg'
			, 'FC03', '15', 'U02', 'LO04', 'DI05', 'Active')

INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL01', '00:00', '01:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL02', '01:00', '02:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL03', '02:00', '03:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL04', '03:00', '04:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL05', '04:00', '05:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL06', '05:00', '06:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL07', '06:00', '07:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL08', '07:00', '08:00', 1)
INSERT INTO tblSlots(slotId, timeStart, timeEnd, [status]) VALUES ('SL09', '08:00', '09:00', 1)
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

INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD01', 'SL01', 'FI01', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD02', 'SL01', 'FI02', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD03', 'SL01', 'FI03', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD04', 'SL01', 'FI04', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD05', 'SL02', 'FI01', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD06', 'SL02', 'FI02', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD07', 'SL03', 'FI03', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD08', 'SL03', 'FI04', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD09', 'SL03', 'FI01', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD10', 'SL03', 'FI02', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD11', 'SL03', 'FI03', 1)
INSERT INTO tblSlotDetail(slotDetailId, slotId, fieldId, [status]) VALUES ('SD12', 'SL02', 'FI04', 1)

INSERT INTO tblFoodCategory(categoryFoodId, categoryFoodName, [status]) VALUES ('OC01', N'Đồ ăn', 1)
INSERT INTO tblFoodCategory(categoryFoodId, categoryFoodName, [status]) VALUES ('OC02', N'Đồ uống', 1)

INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO01', N'Bánh mì pate', 'https://cdn.tgdd.vn/2020/10/content/7-750x600.jpg'
					, 'OC01', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO02', N'Bánh mì thịt nướng', 'https://nghethuat365.com/wp-content/uploads/2021/09/Cach-an-banh-mi-kep-thit-khong-lo-bi-beo.jpg'
					, 'OC01', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO03', N'Bánh snack bí đỏ', 'https://cf.shopee.vn/file/6c2e631ffd968980730f47d3d88a7f2a'
					, 'OC01', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO04', N'Bánh snack mực lăn', 'https://cdn.tgdd.vn/Products/Images/3364/193601/bhx/snack-muc-lan-muoi-ot-poca-goi-37g-201911061537386523.jpg'
					, 'OC01', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO05', N'Nước suối', 'https://hoangquanhangnhat.com/wp-content/uploads/2020/09/n%C6%B0%E1%BB%9Bc-su%E1%BB%91i.png'
					, 'OC02', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO06', N'Nước chanh muối', 'https://toplist.vn/images/800px/nuoc-chanh-muoi-7up-revive-640852.jpg'
					, 'OC02', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO07', N'Cafe cốt dừa', 'https://toplist.vn/images/800px/ca-phe-cot-dua-507453.jpg'
					, 'OC02', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO08', N'Đồ uống kẹo bông', 'https://toplist.vn/images/800px/do-uong-keo-bong-507454.jpg'
					, 'OC02', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO09', N'Trà sữa', 'https://toplist.vn/images/800px/tra-sua-506831.jpg'
					, 'OC02', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO10', N'Trà sữa', 'https://toplist.vn/images/800px/tra-sua-506831.jpg'
					, 'OC02', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO11', N'Trà đào', 'https://toplist.vn/images/800px/tra-dao-4170.jpg'
					, 'OC02', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO12', N'Healthy smoothie', 'https://toplist.vn/images/800px/healthy-smoothie-506835.jpg'
					, 'OC02', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO13', N'Bún đậu', 'https://toplist.vn/images/800px/ngo-dau-an-dem-trang-beo-639411.jpg'
					, 'OC01', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO14', N'Mì Trộn', 'https://toplist.vn/images/800px/ngo-dau-an-dem-trang-beo-639412.jpg'
					, 'OC01', 1)
INSERT INTO tblFoods(foodId, foodName, [image], categoryFoodId, [status]) VALUES ('FO15', N'Cơm trưa', 'https://toplist.vn/images/800px/alo-cu-dem-306758.jpg'
					, 'OC01', 1)

INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD01', 'FO01', 'FI01', '20', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD02', 'FO02', 'FI01', '25', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD03', 'FO05', 'FI01', '10', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD04', 'FO01', 'FI02', '24', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD05', 'FO03', 'FI02', '6', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD06', 'FO04', 'FI02', '5', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD07', 'FO011', 'FI07', '15', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD08', 'FO09', 'FI05', '10', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD09', 'FO08', 'FI010', '9', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD10', 'FO07', 'FI02', '11', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD11', 'FO09', 'FI010', '15', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD12', 'FO010', 'FI05', '2', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD13', 'FO06', 'FI011', '5', 1)
INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, [status]) VALUES ('FD14', 'FO015', 'FI02', '12', 1)

INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO01', '2022-01-24', 'U04', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO02', '2022-03-12', 'U05', '12', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO03', '2022-01-24', 'U04', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO04', '2022-01-24', 'U01', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO05', '2022-01-24', 'U02', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO06', '2022-01-24', 'U03', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO07', '2022-01-24', 'U06', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO08', '2022-01-24', 'U07', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO09', '2022-01-24', 'U01', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO10', '2022-01-24', 'U05', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO11', '2022-01-24', 'U03', '50', 'On-Going')
INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES ('BO12', '2022-01-24', 'U06', '50', 'On-Going')

INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, [status]) VALUES ('BD01', 'BO01', 'FI01', '2022-01-25', 'SD01', '10', 1)
INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, [status]) VALUES ('BD02', 'BO02', 'FI02', '2022-03-14', 'SD02', '12', 1)
INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, [status]) VALUES ('BD03', 'BO03', 'FI03', '2022-01-25', 'SD01', '10', 1)
INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, [status]) VALUES ('BD04', 'BO04', 'FI04', '2022-07-25', 'SD03', '10', 1)
INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, [status]) VALUES ('BD05', 'BO05', 'FI05', '2022-11-01', 'SD01', '10', 1)
INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, [status]) VALUES ('BD06', 'BO06', 'FI06', '2022-10-02', 'SD02', '10', 1)
INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, [status]) VALUES ('BD07', 'BO07', 'FI07', '2022-08-11', 'SD04', '10', 1)
INSERT INTO tblBookingDetail(bookingDetailId, bookingId, fieldId, playDate, slotDetailId, fieldPrice, [status]) VALUES ('BD08', 'BO08', 'FI08', '2022-09-20', 'SD01', '10', 1)


INSERT INTO tblFeedback(feedbackId, content, userId, fieldId, [status]) VALUES ('FB01', N'Sân bóng này được đặt ở nơi thuận tiện cho việc qua lại, sân cỏ tốt, thoáng mát, sạch sẽ.', 'U04', 'FI01', 'Active')
INSERT INTO [dbo].[tblFeedback] ([feedbackId],[content],[userId],[fieldId],[status])
VALUES	('FB02', N'Sân bóng không mới', 'U04', 'FI03', 1)
INSERT INTO [dbo].[tblFeedback] ([feedbackId],[content],[userId],[fieldId],[status])
VALUES	('FB03',N'Sân bóng tệ', 'U05', 'FI01', 1)
INSERT INTO [dbo].[tblFeedback] ([feedbackId],[content],[userId],[fieldId],[status])
VALUES	('FB04', N'Sân bóng mới', 'U04', 'FI03', 1)
INSERT INTO [dbo].[tblFeedback] ([feedbackId],[content],[userId],[fieldId],[status])
VALUES	('FB05', N'Sân bóng phục vụ tốt', 'U05', 'FI07', 1)
INSERT INTO [dbo].[tblFeedback] ([feedbackId],[content],[userId],[fieldId],[status])
VALUES	('FB06', N'phục vụ không tố', 'U04', 'FI07', 1)
INSERT INTO [dbo].[tblFeedback] ([feedbackId],[content],[userId],[fieldId],[status])
VALUES	('FB07', N'sân dơ', 'U04', 'FI08', 1)
