import me.chanjar.weixin.mp.api.*
import me.chanjar.weixin.mp.api.impl.*
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.joda.time.*
import java.sql.*
import java.util.concurrent.*
import java.util.concurrent.atomic.*

// teh
//var appid = "wxf0684115d72889ab"
//var appsecret = "ba2bd96a753d089068486bbb14cabfd8"


// jte
//var appid = "wx5c28593ae5cab8da"
//var appsecret = "1df1bbe7e5dda0852d3b821fc2f6bac0"

//  110.53.222.61,

var appid = "wx1fc54b9f82925927"
var appsecret = "33c31866bfab46dfb5cf9bf09004c146"
var partner = "10032729"
var partnerkey = "sdd433ji894jf834y78erh75489fgpp4"


fun main() {
//location: gz-cdb-e4xc26fs.sql.tencentcdb.com:62642
//username: root
//passoword: W1eEa9S7sFt62z23


    jdbc

    val count = AtomicInteger(0)

    Database.connect("jdbc:mysql://192.168.10.203:3306/jte?useSSL=false",
            driver = "com.mysql.jdbc.Driver",
            user = "root", password = "xyz11111111")
    val executor = Executors.newFixedThreadPool(20)


    val config = WxMpInMemoryConfigStorage()
    config.appId = appid // 设置微信公众号的appid
    config.secret = appsecret // 设置微信公众号的app corpSecret
    config.token = "..." // 设置微信公众号的token
    config.aesKey = "..." // 设置微信公众号的EncodingAESKey

    val wxService = WxMpServiceImpl()// 实际项目中请注意要保持单例，不要在每次请求时构造实例，具体可以参考demo项目
    wxService.wxMpConfigStorage = config

// 用户的openid在下面地址获得
// https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=用户管理&form=获取关注者列表接口%20/user/get

    var nextOpenid: String? = null
    while (true) {
        val userList = wxService.userService.userList(nextOpenid)
        println("======= ${userList.count} ${userList.total}")
        userList.openids
//                .take(50)
                .chunked(99)
                .forEach { openids ->
                    executor.submit {
                        var users = wxService.userService
                                .userInfoList(openids)
//                                .map { wxMpUser ->
////                            update topenid set unionid = "${wxMpUser.unionId}" where openid =  "${wxMpUser.openId}";
//                                    """
//
//                            """.trim()
//                                }

                        transaction(Connection.TRANSACTION_READ_UNCOMMITTED, 1) {

                            commit()
                        }

                    }
                    Unit
                }
        if (userList.count != 10000) {
            break
        } else {
            nextOpenid = userList.nextOpenid
            Thread.sleep(2000)
        }
    }

    executor.shutdown()

}

/*
CREATE TABLE `topenid` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`openid` varchar(50) NOT NULL COMMENT '1表示微信，2表示QQ',
`loginName` varchar(128) NOT NULL,
`openType` char(2) DEFAULT NULL COMMENT '1表示微信，2表示QQ 3表示电脑端 4表示小程序',
`createDate` datetime DEFAULT NULL,
`appid` varchar(32) DEFAULT NULL COMMENT '公众号appid，区别不同公众号微信绑定（仅做备注参考作用，值可能有错误）',
`unionid` varchar(50) DEFAULT NULL COMMENT 'unionid',
PRIMARY KEY (`id`),
UNIQUE KEY `UK_topenid_openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=89149 DEFAULT CHARSET=utf8;
 */
object TOpenId : IntIdTable("topenid") {
    val unionid = varchar("unionid", 50)
    val loginName = varchar("loginName", 50).nullable()
    val openType = varchar("openType", 2).default("1")
    val createDate = datetime("createDate").default(DateTime.now())
    val wxappid = varchar("appid", 32)
    val openid = varchar("openid", 50)
}