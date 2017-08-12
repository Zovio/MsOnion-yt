@rem ==========================================
@rem 广州市两棵树网络科技有限公司
@rem 2017-04-18
@rem http://www.yunyangtao.com/web/index.html
@rem ==========================================

@echo off
setlocal

@rem ==========================================
@rem 洋桃商城平台，分布式架构，maven build
@rem 相关环境有：dev、test、sta、pre、pro
@rem  pro(production、生产环境) , pre(preview、预发布环境) , test（测试环境） , dev(develop、开发环境) , sta (stable、稳定环境)
@rem ==========================================

@rem call mvn clean install
@rem call mvn -f msyt-all-pom.xml clean install -Dmaven.test.skip=true -P dev
@rem call mvn -f msyt-all-pom.xml clean install -Dmaven.test.skip=true
@rem call mvn -f msyt-all-pom.xml clean install -Dmaven.test.skip=true -P dev
call mvn -f msyt-all-pom.xml clean install -Dmaven.test.skip=true -P test
:end
endlocal
pause