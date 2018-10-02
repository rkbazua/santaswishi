# santaswishi

Proyecto mascotas WishiPets.

Arquitectura de directorios:

/schema: Todos los archivos relacionados con la persistencia en MongoDB y los catálogos en S3
/schema/base: Archivos json para generar las colecciones de MongoDB 
/schema/queries: Consultas comunes a realizar en MongoDB 
/schema/catalogs: Catálogos en formato json que se almacenan en S3: especies, razas, municipios, etc.

/bin: Todos los programas que pasan por un proceso de build. Por lo general se convertiran en funciones lambda
/bin/{nombrePrograma}: Cada función o programa va en un subdirectorio. El objetivo es que CodeBuild detecte estos cambios, y haya un Build para cada demonio.
/bin/aws: Todos los contenidos específicos de tecnologías AWS
/bin/aws/cfn: Plantillas de CloudFormation en formato YAML 
/bin/aws/codepipeline: Archivos de configuración de codepipeline, etc.
/bin/apps: Todos los programas de front end
/bin/apps/android: Fuente de la aplicación de android 
/bin/apps/ios: Fuente de la aplicación de ios 
/bin/apps/web: Fuente de la aplicación web 