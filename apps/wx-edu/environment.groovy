//def context = 'ihoubo.cn'
def $ip = '127.0.0.1'
def $domain = 'wangxiao.io'
def $memcached = 'ihoubo.com'
def $_port = ':9090';
def $option = [
        domain   : '.' + $domain,
        memcached: '.' + $memcached + ":11211",
        db_driver: 'com.mysql.jdb.Driver',
        db_url   : 'jdbc:mysql://192.168.99.100:3308/wx-edu?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull',
        db_user  : 'admin',
        db_pwd   : 'abcd1234',
//        context  : 'http://' + $domain,
        context  : 'http://' + $domain,
        upload   : 'http://upload.' + $domain,
        static   : 'http://static.' + $domain,
        exam     : 'http://exam.' + $domain,
        sns      : 'http://sns.' + $domain,
        cms      : 'http://cms.' + $domain,
        video    : 'http://video.' + $domain,
        wz       : 'http://wz.' + $domain
]
environments {
    dev {

        jdbc {
            driverClassName = $option.db_driver
            url = $option.db_url
            username = $option.db_user
            password = $option.db_pwd
            maxPoolSize = 100
            minPoolSize = 10
            initialPoolSize = 5
            idleConnectionTestPeriod = 1800
            maxIdleTime = 3600
        }
        redis {
            ip = $ip
            port = 6379
            //最大分配对象
            pool.maxActive = 100
            //jedis最大保存idel状态对象数 #
            pool.maxIdle = 30
            //jedis池没有对象返回时，最大等待时间 毫秒 #
            pool.maxWait = 3000
            //password 暂时不需要
            password = "43b6f9b82c71c13063a523c0dcbc6db8fc0ac8a0"
        }
        memcached {
            server1 = "memcached:11211"
            server2 = "memcached:11211"
            opTimeout = 3
            opTimeoutBulk = 3
            retry = 1
            readBufSize = 16384
            opQueueLen = 16384
            expHour = 24
            // 0 不使用 1 使用
            isUse = 1
        }
        email {
            host = "smtp.163.com"
            username = "ihoubo@163.com"
            password = ""
        }
        project {
            contextPath = $option.context + $_port
            examPath = $option.exam
            snsPath = $option.sns
            //图片、CSS、JS等静态资源文件地址
            staticServer = $option.context + $_port
            //静态资源上传服务地址
            uploadImageServer = $option.upload
            //静态资源访问地址
//            staticImage = $option.static + port
            staticImage = $option.context + $_port
            domain = $option.domain + $_port
            projectName = "grow-edu"
//            videoPath = "http://v.xue.com"
            videoPath = $option.video
            wzPath = $option.wz

        }
        hessian {
            auth = "hessainAuth"
        }
        lucene {
            luceneIndexDir = "/data/htdocs/lucene/eduplat/index/{0}"
            luceneIndexDirBak = "/data/htdocs/lucene/eduplat/luceneIndexBak/{0}"
            alertemail = "master@ihoubo.com"
        }

        alipay {
            // 合作身份者id，以2088开头的16位纯数字
            defaultPartner = 2088123412341234
            defaultSeller = "xxxx@xxxx.com"
            // 商户私钥，自助生成即rsa_private_key.pem中去掉首行，最后一行，空格和换行最后拼成一行的字符串 rsa_private_key.pem这个文件等你申请支付宝签约成功后，按照文档说明你会生成的
            privateKey = ""
            // 支付宝公钥,用签约支付宝账号登录ms.alipay.com后，在密钥管理页面获取
            publicKey = ""

        }

    }
    // 发布版
    prod {}
}
