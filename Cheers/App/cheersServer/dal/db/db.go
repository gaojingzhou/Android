package db

import (
	"errors"
	"../model"
	_ "github.com/go-sql-driver/mysql"
	"github.com/jinzhu/gorm"
)

func GetChatList(username string) ([]*model.ChatList, int, error) {
	db, err := gorm.Open("mysql", "root:1234@/userdb?charset=utf8&parseTime=True&loc=Local")
	if err != nil {
		return nil, 0, errors.New("connect to database failed.")
	}
	defer db.Close()
	chat_list := make([]*model.ChatList, 0)

	db.Where("user_name = ?", username).Find(&chat_list)

	return chat_list, len(chat_list), nil
}

func AddChatLilst(username, chatname string, imgurl int, chatinfo, time string, chatid int) error {
	db, err := gorm.Open("mysql", "root:1234@/userdb?charset=utf8&parseTime=True&loc=Local")
	if err != nil {
		return errors.New("connect to database failed.")
	}
	defer db.Close()

	new_chat := &model.ChatList{UserName:username, ChatName:chatname,  ImgUrl:imgurl, ChatInfo:chatinfo, Time:time, ChatId:chatid}
	db.Create(&new_chat)
	return nil
}