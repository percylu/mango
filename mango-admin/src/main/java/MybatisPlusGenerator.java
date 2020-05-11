import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author 卢水柏
 * @company WeeHai
 * @date 2019/12/22 5:34 下午
 **/
public class MybatisPlusGenerator {
    public static void main(String[] args) {

        // 全局配置
        GlobalConfig globalConfig = globalConfig();

        // 数据源配置
        DataSourceConfig dataSourceConfig = dataSourceConfig();

        // 策略配置
        StrategyConfig strategyConfig = strategyConfig();

        // 包名策略配置
        PackageConfig packageConfig = packageConfig();

        // 整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setPackageInfo(packageConfig);

        autoGenerator.execute();

    }

    /**
     * 全局配置
     *
     * @return
     */
    private static GlobalConfig globalConfig() {
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false) // 是否开启AR模式
                .setAuthor("卢水柏") // 作者
                .setOutputDir("/Users/percylu/mango/src/main/java") //生成路径
                .setFileOverride(true) //文件是否覆盖
                .setIdType(IdType.AUTO) // 主键策略
                .setServiceName("%sService") //默认情况下生成的Service接口的名字首字母都带有I
                .setBaseResultMap(true) // 是否生成基本的sql中的ResultMap
                .setBaseColumnList(true); // 是否生成基本的sql列

        return config;
    }

    /**
     * 数据源配置
     *
     * @return
     */
    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsconfig = new DataSourceConfig();
        // 设置数据库类型
        dsconfig.setDbType(DbType.MYSQL);
        dsconfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dsconfig.setUrl("jdbc:mysql://127.0.0.1:3306/mango?userSSL=false");
        dsconfig.setUsername("root");
        dsconfig.setPassword("Lu137849s");
        return dsconfig;
    }

    /**
     * 策略配置
     * @return
     */
    private static StrategyConfig strategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true); // 是否大写命名
        strategyConfig.setTablePrefix("sys_"); // 表前缀
        strategyConfig.setNaming(NamingStrategy.underline_to_camel); // 从数据库表到文件的命名策略
        strategyConfig.setEntityLombokModel(true);

        strategyConfig.setInclude(new String[]{"sys_user","sys_config","sys_dept","sys_dict",
                "sys_log","sys_login_log","sys_menu","sys_role","sys_role_dept","sys_role_menu","sys_user_role"}); // 对应数据库所需要生成的表名
        return strategyConfig;
    }

    /**
     * 包名策略配置
     * @return
     */
    private static PackageConfig packageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.weehai.mango"); // 所需要生成的包下
        packageConfig.setEntity("model"); // 设置实体生成包名
        packageConfig.setController("controller"); // 设置控制层包名，以下以此类推
        packageConfig.setService("service");
        packageConfig.setMapper("dao");
        packageConfig.setXml("sqlmap");
        return packageConfig;
    }
}


