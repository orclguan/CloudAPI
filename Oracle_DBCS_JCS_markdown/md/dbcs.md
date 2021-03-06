## 1、Oracle数据库云服务通用介绍

Oracle数据库云服务（DBCS）～适用于任何负载、任何业务的企业级数据库云平台

作为Oracle公有云的一部分，Oracle数据库云服务部署在包括通用X86计算资源或Oracle Exadata的企业级基础架构中，并提供：

-   卓越的性能和可用性

-   用于数据冗余的三重存储镜像

-   完整的系统备份和恢复

此外，Oracle 云具有全天候、多语种支持，以确保不同地区的客户需求均能得到满足。

-   位于多个地理位置的数据中心

-   行业级物理和逻辑安全性

## 2、Oracle数据库云服务特性介绍

-   具备Oracle数据库的全部功能，包括SQL和PL/SQL支持

-   专用的虚拟机（附带预配置的数据库实例或专用模式）

-   通用大内存计算模型

-   管理选项灵活多样，即可自行管理， 也可完全由 Oracle 管理

-   通过快速供应及易用的云工具简化了管理

-   用户信息库和一次性登录(SSO )的集成式身份管理

## 3、Oracle数据库云服务多版本选择

企业无论部署生产负载还是部署开发与测试，都可以在多个版本中进行选择，包括：

| 版本                     | 特征                                       |
| ---------------------- | :--------------------------------------- |
| **标准版1**               | Transparent Data Encryption (TDE)<br/>Full database instance<br/>Up to 16 OCPUs |
| **企业版（不含数据库选件）**       | Transparent    Data Encryption (TDE)<br/>Data    Guard<br/>All    standard EE features |
| **企业高性能版（包含大部分数据库选件）** | Multitenant<br/>Data    Guard<br/>Partitioning<br/> Advanced    Compression<br/>Advanced    Security <br/>Label    Security<br/>Database    Vault<br/>Real    Application Testing<br/>OLAP,    Analytics, Spatial and Graph<br/>Management    Packs |
| **企业极致性能版（包含所有数据库选件）** | Real    Application Clusters (RAC) <br/>In    Memory<br/>Active    Data Guard<br/>Others    all… |

## 4、Oracle数据库云服务能力分析

![](images/p1.png){width="10" height="10"}

### 4.1、自动供应

![](images/p2.png)

| ![](images/s1.png) | 高效率、便捷供应数据库服务       |
| ------------------ | ------------------- |
| ![](images/s2.png) | **批量工作实现标准化**       |
| ![](images/s3.png) | **创新、可行的新的数据库供应方式** |

### 4.2、自动基础设施与数据库管理

| ![](images/s4.png) | 计算资源                  | CPU 和 内存 随需分配        |
| ------------------ | --------------------- | -------------------- |
| ![](images/s5.png) | **块和对象存储**            | **增容、归档（加密）**        |
| ![](images/s6.png) | **升级和补丁**             | **自动化升级**            |
| ![](images/s7.png) | **Point-in-time 恢复**  | **从人为错误中恢复**         |
| ![](images/s8.png) | **Point-to-point 网络** | **仅打开必需的端口**         |
| ![](images/s9.png) | **服务集成**              | **当需要时，增加其他PaaS 服务** |

### 4.3、安全

![](images/p3.png)

|                             | 11g  | 12c  | Cloud Edition |
| --------------------------- | ---- | ---- | ------------- |
| Transparent Data Encryption | √    | √    | EE, HP, EP    |
| Database Vault*             | √    | √    | HP, EP        |
| Audit Vault*                | √    | √    | HP, EP        |
| Database Firewall*          | √    | √    | HP, EP        |
| Key Vault                   | √    | √    | EE, HP, EP    |
| Data Redaction              | √    | √    | EE, HP, EP    |
| Data Masking                |      | √    | EE, HP, EP    |
| Auditing                    | √    | √    | EE, HP, EP    |

### 4.4、高可用性

![](images/p4.png)

|                            | 11g  | 12c  | Cloud Edition |
| -------------------------- | ---- | ---- | ------------- |
| Real Application Clusters* | √    | √    | EP            |
| Data Guard                 | √    | √    | EE, HP, EP    |
| Active Data Guard*         | √    | √    | EP            |
| Golden Gate*               | √    | √    | HP, EP        |
| Secure Backup              |      | √    | EE, HP, EP    |
| Online Reorganization      |      | √    | EE, HP, EP    |
| Edition-Based Redefinition |      | √    | EE, HP, EP    |
| Flashback, Total Recall    | √    | √    | EE, HP, EP    |
| Automated Rolling Upgrade* | √    | √    | EE, HP, EP    |
| Failover to the Cloud*     | √    | √    | EE, HP, EP    |

### 4.5、高性能

![](images/p5.png)

|                                 | 11g  | 12c  | Cloud Edition |
| ------------------------------- | ---- | ---- | ------------- |
| Multi version read consistency  | √    | √    | EE, HP, EP    |
| Row level locking               | √    | √    | EE, HP, EP    |
| In-Memory                       |      | √    | EP            |
| Compression                     | √    | √    | HP, EP        |
| Partitioning                    | √    | √    | EE, HP, EP    |
| Hadoop, Big Data SQL, R*        |      | √    | Big Data      |
| Cost based query optimizer      | √    | √    | EE, HP, EP    |
| Scale-out with Oracle RAC*      | √    | √    | EP            |
| Exadata Smartscan & Infiniband* | √    | √    | Exadata       |

### 4.6、使用多租户选件简化整合/部署/集成

![](images/p6.png)

|                                          | 12c  | Cloud Edition |
| ---------------------------------------- | ---- | ------------- |
| PDB Deployment                           | √    | HP, EP        |
| PDB Cloning                              | √    | HP, EP        |
| PDB Self Service App                     | √    | HP, EP        |
| PDB Migration                            | √    | EE, HP, EP    |
| High Density Consolidation               | √    | HP, EP        |
| Automated Provisioning and  Configuration | √    | HP, EP        |
| PDB Resource Management                  | √    | HP, EP        |

### 4.7、数据库应用开发特性

![](images/p7.png)

| SQL and PL/SQL      | 11g  | 12c  | Cloud Edition |
| ------------------- | ---- | ---- | ------------- |
| Java in Database    | √    | √    | EE, HP, EP    |
| Analytics           | √    | √    | EE, HP, EP    |
| Text                | √    | √    | HP, EP        |
| Regular Expressions | √    | √    | EE, HP, EP    |
| Spatial             | √    | √    | EE, HP, EP    |
| Pattern Matching    |      | √    | EE, HP, EP    |
| Database XML        | √    | √    | EE, HP, EP    |
| Database JSON       |      | √    | EE, HP, EP    |
| AQ                  | √    | √    | EE, HP, EP    |

## 5、Oracle数据库云服务开发与管理终端

-   在网站cloud.oracle.com 上数据库服务终端

-   存储终端

-   网络终端

-   企业管理器快捷版

-   On Premise环境中完整的企业管理器

-   数据库云服务监控工具

-   GlassFish 管理终端

-   SQL Developer

-   REST 数据服务

-   Appliction Express (APEX)

## 6、使用场景

-   数据库供应：开发、测试、生产、数据库特性评估

-   迁移数据到Oracle数据库云

-   使用备份/恢复服务保护数据

-   部署数据库应用到云端

-   改变计算资源

-   增加存储

-   补丁和升级

-   克隆12c 的可插拔数据库 (PDB)

## 7、优势分析

**数据库**

| **数据库版本**                                |
| ---------------------------------------- |
| 提供 Oracle 数据库 11g、12.1  和 12.2 的专用数据库实例，您可以选择标准包、企业包、高性能包或极致性能包。 |
| **服务包**                                  |
| 标准包和企业包具有各自的内部部署功能；此外，在这两种包中都增加了透明数据加密功能。高性能包增加了除 RAC、内存数据库和活动数据卫士以外的所有数据库选件。极致性能包具有全部选件。 |
| **数据库容器化**                               |
| Oracle  数据库 12c 包括 Oracle 多租户选件，可用于管理可插拔式数据库。 |
| **开发运维**                                 |
| 创建测试主数据并屏蔽敏感数据。创建克隆和执行生命周期管理，实现敏捷开发。     |

**数据访问**

| **管理访问**                                 |
| ---------------------------------------- |
| 通过 SSH、SQL 开发人员工具、数据泵、SQL*Plus 以及其他工具对数据进行管理访问。 |
| **数据访问**                                 |
| 使用任何可用的 Oracle 客户端语言库来访问您的专用实例，包括 Oracle Net  (SQL*Net)、JDBC、JSON 及其他驱动程序。 |
| **工具**                                   |
| 可使用企业管理器、SQL 开发人员工具、Application Express 或者其他 Oracle 工具或第三方工具。 |
| **安全网络访问**                               |
| 使用 IPsec VPN 选件进行安全访问。                   |

**管理**

| **扩展**                                   |
| ---------------------------------------- |
| 通过 Web 控制台（或 REST API）控制存储和计算能力扩展。提供超出订阅的突发容量和计量式计算服务，支持您经济高效地处理高峰负载。 |
| **打补丁和升级**                               |
| 使用 Web 控制台（或 REST API）中内置的自动化打补丁工具，轻松应用每季度的数据库补丁程序。使用简单的迁移过程来验证升级。 |
| **安全**                                   |
| 使用计算服务的 Web 控制台（或 REST API）管理安全规则和 IPsec 列表，实现灵活的企业级网络安全。利用所有 Oracle 数据库深度防御安全功能，实现全面的数据安全。 |
| **备份选项**                                 |
| 安排自动化备份，加快本地存储、Oracle 云对象存储或两者的备份速度。基于备份副本实例化新服务，以便进行开发和测试。 |
| **弹性**                                   |
| 根据自己的需要增加或减少计算资源、内存或存储空间                 |


