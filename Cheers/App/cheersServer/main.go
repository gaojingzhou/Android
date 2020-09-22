package main
import (
	"./views"

	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.Default()
	r.GET("/ping", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "success",
		})
	})

	r.POST("/chat_list", views.AddChatList)
	r.GET("/chat_list", views.GetChatList)


	r.Run(":8081")
}