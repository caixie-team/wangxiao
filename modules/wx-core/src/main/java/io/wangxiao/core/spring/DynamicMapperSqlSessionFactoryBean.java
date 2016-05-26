package io.wangxiao.core.spring;

import io.wangxiao.core.codebuilder.MybatisCRUDBuilder;
import io.wangxiao.core.model.BaseIncrementIdModel;
import io.wangxiao.core.model.BaseModel;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 继承自SqlSessionFactoryBean类，实现Mapper基本操作模块化功能
 * <p>
 * 在一般系统中，每个Model都会有部分基本操作，这些操作模式基本相同，所以在我们系统内部将其抽象出来，通过动态的生成Mapper的形式注入到Mybatis中。<br/>
 * 对于生成的mapper文件，在{@link #setMapperLocations(Resource[])}方法中加入到Factory map locations中，让Ibatis自行处理。
 * </p>
 *
 * @see SqlSessionFactoryBean
 */
public class DynamicMapperSqlSessionFactoryBean extends SqlSessionFactoryBean {
    private static final Log LOGGER = LogFactory.getLog(DynamicMapperSqlSessionFactoryBean.class);

//    private static final Logger LOGGER = Logger.getLogger(DynamicMapperSqlSessionFactoryBean.class);

    private String tablePrefix;
    private String mapperModules;
    private String path = "/cc/anbi/";
    private String path_suffix = "/*-mapper.xml";

    /**
     * 在此方法中加入了动态mapper 文件插入
     *
     * @param mapperLocations mapper 资源文件列表
     */
    @Override
    public void setMapperLocations(Resource[] mapperLocations) {
        List<Resource> resList = new ArrayList<Resource>();

//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //~Star
        // TODO： 对于资源文件的获取方式需要改进，此段没有测试成功，待定！！！ @basil
/*        if (mapperModules != null){
            String[] modules = mapperModules.split(",");
            for (String module : modules){
                try {
                    Resource[] reses = SpringContextHolder.getApplicationContext().getResources(path + module + path_suffix);
//                    System.out.println(readResourceAsString(reses[0]));
//                    ArrayUtils.addAll(mapperLocations, reses);
                    Collections.addAll(resList, reses);
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
//                    mapperLocations =  SpringContextHolder.getApplicationContext().getResources(path + module + path_suffix);
            }
        }*/
//                    ArrayUtils.addAll(mapperLocations, resList.toArray());

        //~END

        //生成mapper资源文件对象
        List<MapperSplitter> resources = productModelMapperResources();
        //按照是否有cacheNameSpaceRef进行排序，没有的排在前面！！Mybatis的bug：如果引用了别的缓存命名空间，而被引用的命名空间在引用者后面被解析的话，缓存设置无效。
        Collections.sort(resources, (o1, o2) -> {
            if (o1.isCacheNameSpaceRef() && o2.isCacheNameSpaceRef()) return 0;
            if (!o1.isCacheNameSpaceRef() && !o2.isCacheNameSpaceRef()) return 0;
            if (o1.isCacheNameSpaceRef() && !o2.isCacheNameSpaceRef()) return 1;
            return -1;
        });
        //将原始source和生成的source整合
        try {
            super.setMapperLocations(addResourcesByArray(mapperLocations, resources));

        } catch (Exception e) {
            e.printStackTrace();
        }
        //开始加载

    }


    private String readResourceAsString(Resource resource) {
        StringBuilder buffer = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()), 1024);

            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    buffer.append(line);
                    buffer.append("\r\n");
                } else break;
            }
            reader.close();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    /**
     * 将自定义的mapper文件加到动态生成的mapper文件中。返回合并后的resource集合。
     *
     * @param sources          资源文件列表
     * @param dynamicResources 动态生成的资源文件
     * @return 添加后的资源
     * @throws Exception
     */
    private Resource[] addResourcesByArray(Resource[] sources, List<MapperSplitter> dynamicResources) throws Exception {
        List<Resource> mergedResources = new ArrayList<Resource>();
        Map<String, MapperSplitter> mapperMap = new HashMap<String, MapperSplitter>();


        //把自定义的mapper文件按namespace进行拆分。目前namespace相同的mapper文件只能自定义到一个文件中去。
        for (Resource resource : sources) {
            String mapperText = readResourceAsString(resource);
            MapperSplitter splitter = new MapperSplitter(mapperText, resource);

            System.out.println(splitter.getNamespace());
            mapperMap.put(splitter.getNamespace(), splitter);
        }
        //将生成的动态mapper文件和自定义的mapper文件进行合并。
        for (MapperSplitter dynamicMapper : dynamicResources) {
            MapperSplitter mapper = mapperMap.get(dynamicMapper.getNamespace());
            if (mapper != null) {
                String finalMapper = dynamicMapper.getMapperText().replace(dynamicMapper.getSqlNodes(), dynamicMapper.getSqlNodes() + mapper.getSqlNodes());
                mapperMap.remove(mapper.getNamespace());
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(finalMapper);
                }
                mergedResources.add(new InputStreamResource(new ByteArrayInputStream(finalMapper.getBytes("UTF-8")), mapper.getNamespace()));
            } else { //如果同名的nampespace下没有自定义的mapper文件，则加入动态生成的mapper文件
                mergedResources.add(new InputStreamResource(new ByteArrayInputStream(dynamicMapper.getMapperText().getBytes("UTF-8")), dynamicMapper.getNamespace()));
            }
        }
        //加入所有用户自定义的和动态生成合并后剩下的用户自定义的mapper文件。
        mergedResources.addAll(mapperMap.values().stream().map(MapperSplitter::getResource).collect(Collectors.toList()));


        return mergedResources.toArray(new Resource[mergedResources.size()]);


    }

    /**
     * 根据当前model类，自动生成mapper对应的基本文件
     */
    private List<MapperSplitter> productModelMapperResources() {
        List<Class<?>> list = getAllBaseClass();
		LOGGER.debug("classes size is " + list.size());
        //根据class生成相应的Mapper文件
        List<MapperSplitter> mappers = new ArrayList<MapperSplitter>();
        //开始生成
        for (Class<?> aList : list) {
            MybatisCRUDBuilder mybatisCRUDBuilder = new MybatisCRUDBuilder();

            // 设置由属性文件注入的表前缀
            mybatisCRUDBuilder.setTablePrefix(tablePrefix);

            String crudMapper = mybatisCRUDBuilder.buildByClass(aList);
            MapperSplitter splitter = new MapperSplitter(crudMapper);
            splitter.setCacheNameSpaceRef(aList.isAnnotationPresent(CacheNamespaceRef.class));
            mappers.add(splitter);
        }
        return mappers;
    }

    /**
     * 获取JVM中所有Model类列表
     *
     * @return Model类列表
     */
    private List<Class<?>> getAllBaseClass() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(Thread.currentThread().getContextClassLoader());
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        List<Class<?>> classList = new ArrayList<Class<?>>();
        try {
            Resource[] rs = resolver.getResources("classpath*:co/bluepx/edu/**/entity/**/*.class");
            for (Resource resource : rs) {
                String className = metadataReaderFactory.getMetadataReader(resource).getClassMetadata().getClassName();
                Class<?> clazz;
                try {
                    clazz = Class.forName(className);
                    //判断当前cl是否是BaseModel的子类，且不是BaseModel
                    if (BaseIncrementIdModel.class.isAssignableFrom(clazz) && clazz != BaseIncrementIdModel.class && clazz != BaseModel.class) {
                        classList.add(clazz);
                    }
                } catch (ClassNotFoundException e) {
                    LOGGER.error("实体" + className + "应该有一个无参的构造函数...");
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classList;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getMapperModules() {
        return mapperModules;
    }

    public void setMapperModules(String mapperModules) {
        this.mapperModules = mapperModules;
    }


    /**
     * 把映射文件按正则表达式进行解析，为动态生成的和自己配的映射文件整合成一个文件做准备
     *
     * @author dou
     */
    private static class MapperSplitter {
        private static Pattern pattern = Pattern.compile("<mapper *namespace *= *\"(.+?)\" *>([\\S\\s]*)</mapper>");
        /**
         * 映射文件对应的文本内容
         */
        private String mapperText;
        /**
         * 映射文件的命名空间
         */
        private String namespace;
        /**
         * 映射文件中的 crud 对应的文本
         */
        private String sqlNodes;

        private boolean cacheNameSpaceRef = false;

        /**
         * 映射文件对应的resource
         */
        private Resource resource;

        MapperSplitter(String mapperText, Resource resource) {
            this(mapperText);
            this.resource = resource;
        }

        MapperSplitter(String mapperText) {
            this.mapperText = mapperText;

            Matcher matcher = pattern.matcher(mapperText);
            if (matcher.find()) {
                this.namespace = matcher.group(1);
                this.sqlNodes = matcher.group(2);
            } else {
                throw new RuntimeException();
            }
        }

        public boolean isCacheNameSpaceRef() {
            return cacheNameSpaceRef;
        }

        public void setCacheNameSpaceRef(boolean cacheNameSpaceRef) {
            this.cacheNameSpaceRef = cacheNameSpaceRef;
        }

        public Resource getResource() {
            return this.resource;
        }

        public String getNamespace() {
            return namespace;
        }

        public String getSqlNodes() {
            return sqlNodes;
        }

        public String getMapperText() {
            return mapperText;
        }


    }

    //TODO: 待用，动态载入mapper文件的工具
    // 如果如Spring接口，就可以使用Spring的Resource对象来实现多次载入，
    // 可在Bean中配置的对象实现ApplicationContextAware接口，
    // 获取ApplicationContext对象的引用，从而使用通配符来加载多个XML文件，示例代码如下：
/*    public static void loadXml(String locations,
                               SqlSessionFactory sqlSessionFactory,
                               ApplicationContext applicationContext) {
        if (locations == null)
            return;
        String[] mapperLocations = locations.split(",");
        Configuration configuration = sqlSessionFactory.getConfiguration();
        for (String location : mapperLocations) {
            try {
                Resource[] resources = applicationContext
                        .getResources(location);
                if (resources != null) {
                    for (Resource resource : resources) {
                        new XMLMapperBuilder(resource.getInputStream(),
                                configuration, resource.toString(),
                                configuration.getSqlFragments()).parse();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/


}
