package views

import (
	"strconv"

	"../dal/db"

	"github.com/gin-gonic/gin"
)

func AddChatList(c *gin.Context) {
	username := c.PostForm("user_name")
	chatname := c.PostForm("chat_name")
	imgurlstr := c.PostForm("img_url")
	chatinfo := c.PostForm("chat_info")
	time := c.PostForm("time")
	chatidstr := c.PostForm("chat_id")

	imgurl, err := strconv.ParseInt(imgurlstr, 10, 64)
	chatid, err := strconv.ParseInt(chatidstr, 10, 64)
	if err != nil {
		c.JSON(200, gin.H{
			"message":       "error",
			"error_message": "invalid friendid.",
		})
		return
	}


	err1 := db.AddChatLilst(username, chatname, int(imgurl), chatinfo, time , int(chatid))

	if err1 != nil {
		c.JSON(200, gin.H{
			"message":       "error",
			"error_message": err1.Error(),
		})
		return
	}

	res := gin.H{
		"message":       "success",
		"error_message": "",
	}
	c.JSON(200, res)
}

