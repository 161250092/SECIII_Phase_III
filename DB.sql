drop database AcceptedTask;
drop database Achievement;
drop database Label;
drop database Map;
drop database Message;
drop database PublishedTask;
drop database User;

create table `WorkerBid`(
`UserId` varchar(20),
`TaskId` varchar(100),
`Radio` double precision,
`Cash` double precision,
`ImageNum` Integer,
`WorkerBidState` varchar(100),
primary key(UserId, TaskId)
);

create database Achievement;

use Achievement;

create table `UserAchievement`(
`UserId` varchar(20),
`ID` int,
`rateOfProgress` double precision,
`Finish` boolean,
`Reward` boolean,
primary key(UserId,ID)
);

create table `AchievementCharacteristic`(
`ID` int,
`Name` varchar(50),
`Description` varchar(200),
`Cash` double precision,
primary key(ID)
);

insert into AchievementCharacteristic values(1,"1_finished","finish 1 task",5);
insert into AchievementCharacteristic values(2,"10_finished","finish 10 tasks",20);
insert into AchievementCharacteristic values(3,"1_ImageLabelFinished","finish 1 ImageLabel",5);
insert into AchievementCharacteristic values(4,"10_ImageLabelFinished","finish 10 ImageLabel",20);
insert into AchievementCharacteristic values(5,"1_FrameLabelFinished","finish 1 FrameLabel",5);
insert into AchievementCharacteristic values(6,"10_FrameLabelFinished","finish 10 FrameLabel",20);
insert into AchievementCharacteristic values(7,"1_AreaLabelFinished","finish 1 AreaLabel",5);
insert into AchievementCharacteristic values(8,"10_AreaLabelFinished","finish 10 AreaLabel",20);

create database Label;

use Label;

create table `iLabel`(
`UserId` varchar(20),
`TaskId` varchar(100),
`iNumber` integer,
`Content` varchar(100),
primary key(UserId, TaskId, iNumber)
);

create table `aLabel_tag`(
`UserId` varchar(20),
`TaskId` varchar(100),
`iNumber` integer,
`aNumber` integer,
`tag` varchar(100),
primary key(userId, TaskId, iNumber, aNumber)
);

create table `aLabel_pixel`(
`UserId` varchar(20),
`TaskId` varchar(100),
`iNumber` integer,
`aNumber` integer,
`pNumber` integer,
`x` double,
`y` double,
primary key(userId, TaskId, iNumber, aNumber, pNumber)
);

create table `fLabel`(
`UserId` varchar(20),
`TaskId` varchar(100),
`iNumber` integer,
`fNumber` integer,
`Content` varchar(100),
primary key(UserId, TaskId, iNumber, fNumber)
);

create database Map;

use map;

create table `TypeCash`(
`TaskType` varchar(100),
`Cash` double precision,
primary key(TaskType)
);

create table `TypePrestige`(
`TaskType` varchar(100),
`Prestige` double precision,
primary key(TaskType)
);

create table `MaxPrestige`(
`Prestige` double precision
);

insert into TypeCash values('ORDINARY_LEVEL_LABEL_REQUIRED', 0.1);
insert into TypeCash values('HIGH_LEVEL_LABEL_REQUIRED', 0.3);
insert into TypeCash values('VERY_HIGH_LEVEL_LABEL_REQUIRED', 0.5);

insert into TypePrestige values('ORDINARY_LEVEL_LABEL_REQUIRED', 60);
insert into TypePrestige values('HIGH_LEVEL_LABEL_REQUIRED', 80);
insert into TypePrestige values('VERY_HIGH_LEVEL_LABEL_REQUIRED', 90);

insert into MaxPrestige values(100);

create database Message;

use Message;

create Table `AcceptedTaskMessage`(
`MessageId` varchar(100),
`UserId` varchar(20),
`TaskId` varchar(100),
`Cash` double precision,
`State` varchar(30),
`isChecked` boolean,
primary key(MessageId)
);

create Table `AchievementMessage`(
`MessageId` varchar(100),
`UserId` varchar(20),
`AchievementId` varchar(100),
`isChecked` boolean,
primary key(MessageId)
);

create Table `PublishedTaskMessage`(
`MessageId` varchar(100),
`UserId` varchar(20),
`TaskId` varchar(100),
`WorkerId` varchar(20),
`WorkerName` varchar(20),
`Cash` double precision,
`isChecked` boolean,
primary key(MessageId)
);

create Table `BillMessage`(
`MessageId` varchar(100),
`UserId` varchar(20),
`Type` varchar(10),
`Reason` varchar(30),
`Cash` double precision,
`Date` varchar(100),
`isChecked` boolean,
primary key(MessageId)
);

create table `GuyMessage`(
`MessageId` varchar(100),
`WorkerId` varchar(20),
`RequestorId` varchar(20),
`RequestorName` varchar(20),
`TaskId` varchar(100),
`Cash` double precision,
`isChecked` boolean,
primary key(MessageId)
);

create database PublishedTask;

use PublishedTask;

create table `PublishedTask`(
`UserId` varchar(20),
`TaskId` varchar(100),
`LabelType` varchar(30),
`Description` varchar(1000),
`aWorkerNum` integer,
`fWorkerNum` integer,
`State` varchar(30),
primary key(UserId, TaskId)
);

create table `Sample`(
`TaskId` varchar(100),
`Num` integer,
`Index` varchar(100),
primary key(TaskId)
);

create table `FileName`(
`TaskId` varchar(100),
`iNumber` integer,
`Value` varchar(100),
primary key(TaskId, iNumber)
);

create table `Detail`(
`TaskId` varchar(100),
`iNumber` integer,
`Date` varchar(100),
`WorkerNum` integer,
`Discount` varchar(100),
`Cash` double precision,
primary key(TaskId, iNumber)
);

create table `TaskType`(
`TaskId` varchar(100),
`TaskType` varchar(100),
primary key(TaskId)
);

create table `MassTaskDetail`(
`TaskId` varchar(100),
`UnitPrice` double precision,
`Budget` double precision,
`PricingMechanism` varchar(20),
`sDate` long,
`eDate` long,
`isAllocated` boolean,
primary key(TaskId)
);

create Database User;

use User;

create table `Admin`(
`UserId` varchar(20) primary key,
`UserName` varchar(20),
`Password` varchar(20)
);

create table `Worker`(
  `UserId` varchar(20) primary key,
  `UserName` varchar(20),
  `Password` varchar(20),
  `Email` varchar(30),
  `Phone` varchar(20),
  `Cash` double precision,
  `Prestige` double precision,
  `TaskNum` integer
);

create table `Requestor`(
`UserId` varchar(20) primary key,
`UserName` varchar(20),
`Password` varchar(20),
`Email` varchar(30),
`Phone` varchar(20),
`Cash` double precision,
`Prestige` double precision,
`TaskNum` integer
);

insert into Admin values("00000000","admin","123456");