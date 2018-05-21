# psbc sdk

### installation
```
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile("com.github.noob9527:psbc-sdk:master-SNAPSHOT")
    // ...
}
```
### getting started
if you are using spring boot, related service will be autoconfigured, otherwise you have to create them on your own.\

### configuration
| key | required | default |
| - | - | - |
| psbc.jks-file                 | true | psbc_merchant.jks |
| psbc.jks-password             | true | 111111 |
| psbc.merchant-key-alias       | true | merchant_key |
| psbc.merchant-key-password    | true | 111111 |
| psbc.paygate-key-alias        | true | paygate_cert |
| psbc.api-url                  | true | http://103.22.255.201:8443/psbcpay/main/ |
| psbc.log-level                | true | NONE |


