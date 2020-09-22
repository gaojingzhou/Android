package model

type ChatList struct {
	UserName string `gorm:"column:user_name"`
	ChatName string `gorm:"column:chat_name"`
	ImgUrl int `gorm:"column:img_url"`
	ChatInfo string `gorm:"column:chat_info"`
	Time string `gorm:"column:time"`
	ChatId int `gorm:"column:chat_id"`
}

func (ChatList) TableName() string {
	return "chatList"
}
