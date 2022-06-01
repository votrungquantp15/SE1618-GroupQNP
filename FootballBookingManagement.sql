CREATE DATABASE FootballBookingManagement
USE FootballBookingManagement

DROP DATABASE FootballBookingManagement

CREATE TABLE tblRoles
(
	roleID varchar(2) PRIMARY KEY,
	roleName nvarchar(50) NOT NULL,
	[status] [bit] NOT NULL
)

CREATE TABLE tblUsers
(
	userId varchar(10) PRIMARY KEY,
	fullName nvarchar(50) NOT NULL,
	[address] nvarchar(50) NULL,
	birthday date NULL,
	phone varchar(11) NULL,
	email varchar(30) NOT NULL,
	accName nvarchar(50) NOT NULL,
	[password] nvarchar(50) NOT NULL,
	roleId varchar(2) FOREIGN KEY REFERENCES tblRoles(roleId),
	[status] [bit] NOT NULL
)

CREATE TABLE tblCategory
(
	categoryId varchar(10) PRIMARY KEY,
	categoryName nvarchar(50) NOT NULL,
	[status] [bit] NOT NULL
)

CREATE TABLE tblLocation
(
	locationId varchar(10) PRIMARY KEY,
	locationName nvarchar(50) NOT NULL,
	[status] [bit] NOT NULL
)

CREATE TABLE tblFields
(
	fieldId varchar(10) PRIMARY KEY,
	fieldName nvarchar(50) NOT NULL,
	[description] nvarchar(50),
	[image] [nvarchar](max) NULL,
	price money,
	categoryId varchar(10) FOREIGN KEY REFERENCES tblCategory(categoryId),
	userId varchar(10) FOREIGN KEY REFERENCES tblUsers(userId),
	locationId varchar(10) FOREIGN KEY REFERENCES tblLocation(locationId),
	[status] [bit] default '0'
)

CREATE TABLE tblFeedback
(
	feedbackId varchar(10) PRIMARY KEY,
	title nvarchar(50) NOT NULL,
	content nvarchar(50) NOT NULL,
	userId varchar(10) FOREIGN KEY REFERENCES tblUsers(userId),
	fieldId varchar(10) FOREIGN KEY REFERENCES tblFields(fieldId),
	[status] [bit] NOT NULL
)

CREATE TABLE tblBooking
(
	bookingId varchar(10) PRIMARY KEY,
	bookingDate date NOT NULL,
	userId varchar(10) FOREIGN KEY REFERENCES tblUsers(userId),
	[status] [bit] NOT NULL
)

CREATE TABLE tblBookinhDetail
(
	bookingId varchar(10) FOREIGN KEY REFERENCES tblBooking(bookingId),
	fieldId varchar(10) FOREIGN KEY REFERENCES tblFields(fieldId),
	price money,
	[status] [bit] NOT NULL,
	PRIMARY KEY (bookingId, fieldId)
)


INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('AD', 'Admin', 1)
INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('MA', 'Manager', 1)
INSERT INTO tblRoles (roleID, roleName, [status]) VALUES ('US', 'User', 1)

INSERT INTO tblUsers (userID, fullName, [address], birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U1', N'Nguyễn Đức Nhân', '1/1 D1 TPHCM', '2001-06-24', '089x', 'N1@gmail.com', '1', 'AD', 1)
INSERT INTO tblUsers (userID, fullName, [address], birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U2', N'Nguyễn An', '1/2 D2 TPHCM', '2002-11-11', '080x', 'A1@gmail.com', '1', 'MA', 1)
INSERT INTO tblUsers (userID, fullName, [address], birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U3', N'Lê Bình', '5 D3 HN', '2001-12-05', '081x', 'B1@gmail.com', '1', 'US', 1)
INSERT INTO tblUsers (userID, fullName, [address], birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U4', N'Chí Cường', '202/10 D4 CT', '1999-10-15', '090x', 'C1@gmail.com', '1', 'US', 1)
INSERT INTO tblUsers (userID, fullName, [address], birthday, phone, email, accName, [password], roleId, [status]) VALUES ('U5', N'Phạm Danh', '303 D5 HN', '2003-12-31', '091x', 'D1@gmail.com', '1', 'US', 1)

