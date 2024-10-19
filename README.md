# 项目笔记

## 10.19

### 开发接口流程

1. 明确需求->用户如何使用功能
2. 阅读接口文档->明确接口输入和输出
3. 思路分析->分析代码逻辑
4. 开发
5. 测试

### 实现用户注册功能

1. 如果返回的格式是固定的情况下，可以考虑创建一个泛型实体用来返回结果。
2. 使用Lombok，可以自动帮实体类生成getter，setter， toString等函数，并且这很重要。因为SpringBoot自动把返回值转成josn格式需要用这些函数。（需要在pom中引入Lombok的jar包）
3. 在mapper接口中实现对数据的操作时，如果传入的是多个变量，要用`@Param("变量名")`对变量和sql语句做对应，否则会报找不到变量的错误。
整体逻辑：按照用户名查用户，存在则用户名重复，否则可以创建，创建时将密码加密后通过mapper创建记录。

### 接口参数校验

1. 若在接口文档中明确对一些参数的格式做了要求，则需要在Controller中做参数校验。因为使用if,else太繁琐，所以推荐使用`@Pattern(regexp = "^正则表达式$")`，加在接收的参数前自动做格式校验。需要在Controller类的上方加上`@Validated`，这样Spring才会识别到`@Pattern`。（需要在pom中引入spring-boot-starter-validation的jar包）
2. 但是若之作校验，就会直接返回访问500，并不能清晰的反馈问题，所以可以创建全局异常处理器。需要在类上加上`@RestControllerAdvice`，明确这是异常处理控制类。在每个处理异常的方法上加上`@ExceptionHandler(异常.class)`，表明方法处理的异常类，并在方法中实现对异常的处理和反馈，具体操作和Controller类似，异常了就返回这个，没异常就返回那个。
