@rem ==========================================
@rem 广州市两棵树网络科技有限公司
@rem 2017-04-18
@rem http://www.yunyangtao.com/web/index.html
@rem ==========================================

@echo off
setlocal

@rem ==========================================
@rem 洋桃商城平台，分布式架构，maven deploy
@rem ==========================================

@rem call mvn clean install
@rem call mvn -f msyt-all-pom.xml clean deploy -Dmaven.test.skip=true -p dev
call mvn -f msyt-all-pom.xml clean deploy -Dmaven.test.skip=true
:end
endlocal
pause