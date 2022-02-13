USE [OnlineLearningSystem]
GO
/****** Object:  UserDefinedFunction [ChkValidEmail]    Script Date: 2/13/2022 8:20:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [ChkValidEmail](@EMAIL varchar(100))RETURNS bit as
BEGIN     
  DECLARE @bitEmailVal as Bit
  DECLARE @EmailText varchar(100)

  SET @EmailText=ltrim(rtrim(isnull(@EMAIL,'')))

  SET @bitEmailVal = case when @EmailText = '' then 0
                          when @EmailText like '% %' then 0
                          when @EmailText like ('%["(),:;<>\]%') then 0
                          when substring(@EmailText,charindex('@',@EmailText),len(@EmailText)) like ('%[!#$%&*+/=?^`_{|]%') then 0
                          when (left(@EmailText,1) like ('[-_.+]') or right(@EmailText,1) like ('[-_.+]')) then 0                                                                                    
                          when (@EmailText like '%[%' or @EmailText like '%]%') then 0
                          when @EmailText LIKE '%@%@%' then 0
                          when @EmailText NOT LIKE '_%@_%._%' then 0
                          else 1 
                      end
  RETURN @bitEmailVal
END 
GO
/****** Object:  Table [Account]    Script Date: 2/13/2022 8:20:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Account](
	[Username] [varchar](30) NOT NULL,
	[Password] [varchar](max) NULL,
	[Mail] [varchar](50) NOT NULL,
	[Avatar] [varchar](100) NULL,
	[DisplayName] [nvarchar](50) NULL,
	[DateOfBirth] [date] NULL,
	[Sex] [bit] NOT NULL,
	[Description] [nvarchar](300) NULL,
	[Cash in account] [money] NULL,
	[CreatedDate] [date] NULL,
	[Role] [varchar](20) NULL,
	[Status] [varchar](20) NULL,
	[State] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [Chapter]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Chapter](
	[ChapterID] [int] IDENTITY(1,1) NOT NULL,
	[ChapterName] [nvarchar](100) NOT NULL,
	[Semester] [int] NULL,
	[Chapter Content] [nvarchar](300) NULL,
	[SubjectID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ChapterID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Characteristic_Term]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Characteristic_Term](
	[TermID] [int] IDENTITY(1,1) NOT NULL,
	[TermName] [nvarchar](50) NOT NULL,
	[Explain] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[TermID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Constant]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Constant](
	[ConstantID] [int] IDENTITY(1,1) NOT NULL,
	[ConstantName] [nvarchar](50) NOT NULL,
	[Value] [varchar](50) NULL,
	[Unit] [int] NULL,
	[SubjectID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ConstantID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Finance_History]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Finance_History](
	[UserGet] [varchar](30) NOT NULL,
	[Status] [bit] NOT NULL,
	[Money] [money] NOT NULL,
	[Time] [datetime] NOT NULL,
	[Message] [nvarchar](300) NULL,
	[UserSent] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserGet] ASC,
	[Time] ASC,
	[UserSent] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Knowledge]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Knowledge](
	[KnowledgeID] [int] IDENTITY(1,1) NOT NULL,
	[KnowledgeName] [nvarchar](100) NOT NULL,
	[Title] [nvarchar](200) NULL,
	[ChapterID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[KnowledgeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Notification]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Notification](
	[NotificationID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](30) NULL,
	[Content] [nvarchar](200) NULL,
	[Time] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[NotificationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Post]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Post](
	[PostID] [int] IDENTITY(1,1) NOT NULL,
	[TopicID] [int] NOT NULL,
	[UserPost] [varchar](30) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[Title] [nvarchar](100) NOT NULL,
	[Body] [ntext] NOT NULL,
	[Status] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[PostID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [Post_Reply]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Post_Reply](
	[Post_replyID] [int] IDENTITY(1,1) NOT NULL,
	[PostID] [int] NOT NULL,
	[User_reply] [varchar](30) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[Title] [nvarchar](100) NOT NULL,
	[Body] [ntext] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Post_replyID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [Request]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Request](
	[RequestID] [int] IDENTITY(1,1) NOT NULL,
	[Student_sent] [varchar](30) NOT NULL,
	[Tutor_get] [varchar](30) NULL,
	[CreatedTime] [datetime] NOT NULL,
	[Status] [varchar](50) NOT NULL,
	[Cost] [money] NOT NULL,
	[Content] [nvarchar](200) NOT NULL,
	[Image] [varchar](100) NULL,
	[SubjectID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[RequestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Request_Reply]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Request_Reply](
	[RequestID] [int] IDENTITY(1,1) NOT NULL,
	[Tutor_sent] [varchar](30) NOT NULL,
	[Student_get] [varchar](30) NOT NULL,
	[CreatedTime] [datetime] NOT NULL,
	[Content_reply] [nvarchar](200) NOT NULL,
	[Image_reply] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[RequestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Subject]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Subject](
	[SubjectID] [int] IDENTITY(1,1) NOT NULL,
	[SubjectName] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[SubjectID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Topic]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Topic](
	[TopicID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](100) NOT NULL,
	[SubjectID] [int] NOT NULL,
	[Date] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TopicID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Tutor]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Tutor](
	[Username] [varchar](30) NOT NULL,
	[CV] [varchar](100) NOT NULL,
	[SubjectID] [int] NOT NULL,
	[Status] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Vote]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Vote](
	[VoteID] [int] IDENTITY(1,1) NOT NULL,
	[User_sent] [varchar](30) NOT NULL,
	[User_get] [varchar](30) NOT NULL,
	[Stars] [int] NOT NULL,
	[Reason] [nvarchar](200) NULL,
	[RequestID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[VoteID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [Account] ([Username], [Password], [Mail], [Avatar], [DisplayName], [DateOfBirth], [Sex], [Description], [Cash in account], [CreatedDate], [Role], [Status], [State]) VALUES (N'admin1', N'1000:512db3df8d736c077c164e0c016e10c5:efe92d375d24327bcbe75a767e60ce2ace390409561e6e57e7377b09c35e9b92c956efbc27158f716ff8e098cfd4246a50f2e8176ee146063571dbe2d240df33', N'dathp.proxy@gmail.com', NULL, N'admin1', NULL, 0, NULL, 0.0000, CAST(N'2022-02-13' AS Date), N'Student', N'Actived', 0)
GO
ALTER TABLE [Chapter]  WITH CHECK ADD FOREIGN KEY([SubjectID])
REFERENCES [Subject] ([SubjectID])
GO
ALTER TABLE [Constant]  WITH CHECK ADD FOREIGN KEY([SubjectID])
REFERENCES [Subject] ([SubjectID])
GO
ALTER TABLE [Finance_History]  WITH CHECK ADD FOREIGN KEY([UserGet])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Finance_History]  WITH CHECK ADD FOREIGN KEY([UserSent])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Knowledge]  WITH CHECK ADD FOREIGN KEY([ChapterID])
REFERENCES [Chapter] ([ChapterID])
GO
ALTER TABLE [Notification]  WITH CHECK ADD FOREIGN KEY([Username])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Post]  WITH CHECK ADD FOREIGN KEY([TopicID])
REFERENCES [Topic] ([TopicID])
GO
ALTER TABLE [Post]  WITH CHECK ADD FOREIGN KEY([UserPost])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Post_Reply]  WITH CHECK ADD FOREIGN KEY([PostID])
REFERENCES [Post] ([PostID])
GO
ALTER TABLE [Post_Reply]  WITH CHECK ADD FOREIGN KEY([User_reply])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Request]  WITH CHECK ADD FOREIGN KEY([Student_sent])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Request]  WITH CHECK ADD FOREIGN KEY([SubjectID])
REFERENCES [Subject] ([SubjectID])
GO
ALTER TABLE [Request]  WITH CHECK ADD FOREIGN KEY([Tutor_get])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Request_Reply]  WITH CHECK ADD FOREIGN KEY([RequestID])
REFERENCES [Request] ([RequestID])
GO
ALTER TABLE [Request_Reply]  WITH CHECK ADD FOREIGN KEY([Student_get])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Request_Reply]  WITH CHECK ADD FOREIGN KEY([Tutor_sent])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Topic]  WITH CHECK ADD FOREIGN KEY([SubjectID])
REFERENCES [Subject] ([SubjectID])
GO
ALTER TABLE [Tutor]  WITH CHECK ADD FOREIGN KEY([SubjectID])
REFERENCES [Subject] ([SubjectID])
GO
ALTER TABLE [Tutor]  WITH CHECK ADD FOREIGN KEY([Username])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Vote]  WITH CHECK ADD FOREIGN KEY([RequestID])
REFERENCES [Request] ([RequestID])
GO
ALTER TABLE [Vote]  WITH CHECK ADD FOREIGN KEY([User_get])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Vote]  WITH CHECK ADD FOREIGN KEY([User_sent])
REFERENCES [Account] ([Username])
GO
ALTER TABLE [Account]  WITH CHECK ADD CHECK  (([Role] like 'Student' OR [Role] like 'Tutor' OR [Role] like 'Admin'))
GO
ALTER TABLE [Account]  WITH CHECK ADD CHECK  (([Status] like 'Actived' OR [Status] like 'Banned' OR [Status] like 'Verifying'))
GO
ALTER TABLE [Post]  WITH CHECK ADD CHECK  (([Status] like 'Approved' OR [Status] like 'Waiting'))
GO
ALTER TABLE [Request]  WITH CHECK ADD  CONSTRAINT [CK__Request__Status__59063A47] CHECK  (([Status] like 'Approved' OR [Status] like 'Reject' OR [Status] like 'Waiting' OR [Status] like 'On-time' OR [Status] like 'Report'))
GO
ALTER TABLE [Request] CHECK CONSTRAINT [CK__Request__Status__59063A47]
GO
ALTER TABLE [Tutor]  WITH CHECK ADD CHECK  (([Status] like 'Approved' OR [Status] like 'Reject' OR [Status] like 'Waiting'))
GO
/****** Object:  Trigger [fillColumnAfterSignUp]    Script Date: 2/13/2022 8:20:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create trigger [fillColumnAfterSignUp] on [Account]
for insert
as
declare
@username varchar(30)
begin
	if(Exists(select * from inserted))
	begin
		set @username = (select Username from inserted)
	end
	Update Account
		set DisplayName = @username,
		[Cash in account] = 0,
		CreatedDate = GETDATE(),
		[Role] = 'Student',
		[Status] = 'Actived',
		[State] = 0
		where Account.Username  = @username;
end
GO
ALTER TABLE [dbo].[Account] ENABLE TRIGGER [fillColumnAfterSignUp]
GO
