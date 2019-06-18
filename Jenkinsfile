def readProperties()
{

	def properties_file_path = "${workspace}" + "@script/properties.yml"
	def property = readYaml file: properties_file_path
	env.APP_NAME = property.APP_NAME
        env.MS_NAME = property.MS_NAME
        env.BRANCH = property.BRANCH
        env.GIT_SOURCE_URL = property.GIT_SOURCE_URL
	env.GIT_CREDENTIALS = property.GIT_CREDENTIALS
        env.SONAR_HOST_URL = property.SONAR_HOST_URL
        env.CODE_QUALITY = property.CODE_QUALITY
        env.UNIT_TESTING = property.UNIT_TESTING
        env.CODE_COVERAGE = property.CODE_COVERAGE
        env.FUNCTIONAL_TESTING = property.FUNCTIONAL_TESTING
        env.SECURITY_TESTING = property.SECURITY_TESTING
	env.PERFORMANCE_TESTING = property.PERFORMANCE_TESTING
	env.TESTING = property.TESTING
	env.QA = property.QA
	env.PT = property.PT
	
    
}


def buildApp(projectName,msName){
openshift.withCluster() {
        openshift.withProject(projectName) {
            def bcSelector = openshift.selector( "bc", msName) 
            def bcExists = bcSelector.exists()
            if (!bcExists) {
                openshift.newBuild("${GIT_SOURCE_URL}","--strategy=docker")
                sh 'sleep 400'               
            } else {
                sh 'echo build config already exists in development environment,starting existing build'  
                openshift.startBuild(msName,"--wait")                
            } 
           openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-preprod-02-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-pt-01-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-qa-02-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-dev-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-preprod-01-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-pt-02-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-qa-01-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-prod-77-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-prod-99-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-test-01-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-uat-02-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-test-02-apps')
openshiftTag(namespace: projectName, srcStream: msName, srcTag: 'latest', destStream: msName, destTag: 'openbankdemo-uat-01-apps')

        }
}
}

podTemplate(cloud:'openshift',label: 'selenium', 
  containers: [
    containerTemplate(
      name: 'jnlp',
      image: 'cloudbees/jnlp-slave-with-java-build-tools',
      alwaysPullImage: true,
      args: '${computer.jnlpmac} ${computer.name}'
    )])
{
node 
{
   def MAVEN_HOME = tool "MAVEN_HOME"
   def JAVA_HOME = tool "JAVA_HOME"
   env.PATH="${env.PATH}:${MAVEN_HOME}/bin:${JAVA_HOME}/bin"
   stage('Checkout')
   {
       readProperties()
       checkout([$class: 'GitSCM', branches: [[name: "*/${BRANCH}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '${GIT_CREDENTIALS}', url: "${GIT_SOURCE_URL}"]]])
       sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-preprod-02-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-preprod-02.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-pt-01-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-pt-01.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-qa-02-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-qa-02.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-dev-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-dev.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-preprod-01-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-preprod-01.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-pt-02-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-pt-02.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-qa-01-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-qa-01.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-prod-77-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-prod-77.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-prod-99-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-prod-99.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-test-01-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-test-01.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-uat-02-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-uat-02.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-test-02-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-test-02.yaml'
sh 'oc set image --local=true -f Orchestration/deployment.yaml ${MS_NAME}=docker-registry.default.svc:5000/openbankdemo-dev-apps/${MS_NAME}:openbankdemo-uat-01-apps --dry-run -o yaml >> Orchestration/deployment-openbankdemo-uat-01.yaml'

   }

   stage('Initial Setup')
   {
       sh 'mvn clean compile'
   }
   if(env.UNIT_TESTING == 'True')
   {
   	stage('Unit Testing')
   	{
        	sh 'mvn test'
   	}
   }
   if(env.CODE_COVERAGE == 'True')
   {
   	stage('Code Coverage')
   	{
		sh 'mvn package'
   	}
   }
   if(env.CODE_QUALITY == 'True')
   {
   	stage('Code Quality Analysis')
   	{
       		sh 'mvn sonar:sonar -Dsonar.host.url="${SONAR_HOST_URL}"'
   	}
   }
  if(env.SECURITY_TESTING == 'True')
  {
	stage('Security Testing')
	{
		sh 'mvn findbugs:findbugs'
	}	
  }
   
   

   stage('Dev - Build Application')
   {
       buildApp("openbankdemo-dev-apps", "${MS_NAME}")
   }
   stage('Tagging Image for Dev')
   {
       openshiftTag(namespace: 'openbankdemo-dev-apps', srcStream: '$MS_NAME', srcTag: 'latest', destStream: '$MS_NAME', destTag: 'openbankdemo-dev-apps')
   }
   stage('Dev - Deploy Application')
   {
       sh 'oc apply -f Orchestration/deployment-dev.yaml -n=openbankdemo-dev-apps'
       sh 'oc apply -f Orchestration/service.yaml -n=openbankdemo-dev-apps'
       
   }
	
   stage('openbankdemo-test-01 - Tagging Image for Testing')
   {
       openshiftTag(namespace: 'openbankdemo-dev-apps', srcStream: '$MS_NAME', srcTag: 'latest', destStream: '$MS_NAME', destTag: 'openbankdemo-test-01-apps')
   }

	stage('openbankdemo-test-01 - Deploy Application')
	{
	   sh 'oc apply -f Orchestration/deployment-openbankdemo-test-01.yaml -n=openbankdemo-test-01-apps'
    		   sh 'oc apply -f Orchestration/service.yaml -n=openbankdemo-test-01-apps'
	}
	  
	node('selenium')
	{
	   
	stage('Integration Testing')
	{
	    container('jnlp')
	    {
		 checkout([$class: 'GitSCM', branches: [[name: "*/${BRANCH}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '${GIT_CREDENTIALS}', url: "${GIT_SOURCE_URL}"]]])
		 sh 'mvn integration-test'
	    }
	 }
	 }
stage('openbankdemo-test-02 - Tagging Image for Testing')
   {
       openshiftTag(namespace: 'openbankdemo-dev-apps', srcStream: '$MS_NAME', srcTag: 'latest', destStream: '$MS_NAME', destTag: 'openbankdemo-test-02-apps')
   }

	stage('openbankdemo-test-02 - Deploy Application')
	{
	   sh 'oc apply -f Orchestration/deployment-openbankdemo-test-02.yaml -n=openbankdemo-test-02-apps'
    		   sh 'oc apply -f Orchestration/service.yaml -n=openbankdemo-test-02-apps'
	}
	  
	node('selenium')
	{
	   
	stage('Integration Testing')
	{
	    container('jnlp')
	    {
		 checkout([$class: 'GitSCM', branches: [[name: "*/${BRANCH}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '${GIT_CREDENTIALS}', url: "${GIT_SOURCE_URL}"]]])
		 sh 'mvn integration-test'
	    }
	 }
	 }


   stage('openbankdemo-qa-01 - Tagging Image for QA')
   {
       openshiftTag(namespace: 'openbankdemo-dev-apps', srcStream: '$MS_NAME', srcTag: 'latest', destStream: '$MS_NAME', destTag: 'openbankdemo-qa-01-apps')
   }

stage('openbankdemo-qa-01 - Deploy Application')
	   {
		   sh 'oc apply -f Orchestration/deployment-openbankdemo-qa-01.yaml -n=openbankdemo-qa-01-apps'
       		   sh 'oc apply -f Orchestration/service.yaml -n=openbankdemo-qa-01-apps'
	   }
	   node('selenium')
	   {
		stage('Integration Testing')
		{
		    container('jnlp')
		    {
			 checkout([$class: 'GitSCM', branches: [[name: "*/${BRANCH}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '${GIT_CREDENTIALS}', url: "${GIT_SOURCE_URL}"]]])
			 sh 'mvn integration-test'
		    }
		 }
	    }
stage('openbankdemo-qa-02 - Tagging Image for QA')
   {
       openshiftTag(namespace: 'openbankdemo-dev-apps', srcStream: '$MS_NAME', srcTag: 'latest', destStream: '$MS_NAME', destTag: 'openbankdemo-qa-02-apps')
   }

stage('openbankdemo-qa-02 - Deploy Application')
	   {
		   sh 'oc apply -f Orchestration/deployment-openbankdemo-qa-02.yaml -n=openbankdemo-qa-02-apps'
       		   sh 'oc apply -f Orchestration/service.yaml -n=openbankdemo-qa-02-apps'
	   }
	   node('selenium')
	   {
		stage('Integration Testing')
		{
		    container('jnlp')
		    {
			 checkout([$class: 'GitSCM', branches: [[name: "*/${BRANCH}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '${GIT_CREDENTIALS}', url: "${GIT_SOURCE_URL}"]]])
			 sh 'mvn integration-test'
		    }
		 }
	    }


   stage('openbankdemo-pt-01 - Tagging Image for PT')
   {
       openshiftTag(namespace: 'openbankdemo-dev-apps', srcStream: '$MS_NAME', srcTag: 'latest', destStream: '$MS_NAME', destTag: 'openbankdemo-pt-01-apps')
   }


stage('openbankdemo-pt-01 -  Deploy Application')
	 {
		sh 'oc apply -f Orchestration/deployment-openbankdemo-pt-01.yaml -n=openbankdemo-pt-01-apps'
        sh 'oc apply -f Orchestration/service.yaml -n=openbankdemo-pt-01-apps'
	 }
	     
	stage('openbankdemo-pt-01 -  Performance Testing')
	{
		sh 'mvn verify'
	}
stage('openbankdemo-pt-02 - Tagging Image for PT')
   {
       openshiftTag(namespace: 'openbankdemo-dev-apps', srcStream: '$MS_NAME', srcTag: 'latest', destStream: '$MS_NAME', destTag: 'openbankdemo-pt-02-apps')
   }


stage('openbankdemo-pt-02 -  Deploy Application')
	 {
		sh 'oc apply -f Orchestration/deployment-openbankdemo-pt-02.yaml -n=openbankdemo-pt-02-apps'
        sh 'oc apply -f Orchestration/service.yaml -n=openbankdemo-pt-02-apps'
	 }
	     
	stage('openbankdemo-pt-02 -  Performance Testing')
	{
		sh 'mvn verify'
	}


   stage('openbankdemo-uat-01 - Tagging Image for UAT')
   	{
       		openshiftTag(namespace: 'openbankdemo-dev-apps', srcStream: '$MS_NAME', srcTag: 'latest', destStream: '$MS_NAME', destTag: 'openbankdemo-uat-01-apps')
   	}
	stage('openbankdemo-uat-01 - UAT Application')
	 {
		sh 'oc apply -f Orchestration/deployment-openbankdemo-uat-01.yaml -n=openbankdemo-uat-01-apps'
       		sh 'oc apply -f Orchestration/service.yaml -n=openbankdemo-uat-01-apps'
	 }
stage('openbankdemo-uat-02 - Tagging Image for UAT')
   	{
       		openshiftTag(namespace: 'openbankdemo-dev-apps', srcStream: '$MS_NAME', srcTag: 'latest', destStream: '$MS_NAME', destTag: 'openbankdemo-uat-02-apps')
   	}
	stage('openbankdemo-uat-02 - UAT Application')
	 {
		sh 'oc apply -f Orchestration/deployment-openbankdemo-uat-02.yaml -n=openbankdemo-uat-02-apps'
       		sh 'oc apply -f Orchestration/service.yaml -n=openbankdemo-uat-02-apps'
	 }

   
   stage('openbankdemo-preprod-01 - Tagging Image for Pre-Prod')
   	{
       		openshiftTag(namespace: 'openbankdemo-dev-apps', srcStream: '$MS_NAME', srcTag: 'latest', destStream: '$MS_NAME', destTag: 'openbankdemo-preprod-01-apps')
   	}
	stage('openbankdemo-preprod-01 - Preprod Application')
	 {
		sh 'oc apply -f Orchestration/deployment-openbankdemo-preprod-01.yaml -n=openbankdemo-preprod-01-apps'
       		sh 'oc apply -f Orchestration/service.yaml -n=openbankdemo-preprod-01-apps'
	 }
stage('openbankdemo-preprod-02 - Tagging Image for Pre-Prod')
   	{
       		openshiftTag(namespace: 'openbankdemo-dev-apps', srcStream: '$MS_NAME', srcTag: 'latest', destStream: '$MS_NAME', destTag: 'openbankdemo-preprod-02-apps')
   	}
	stage('openbankdemo-preprod-02 - Preprod Application')
	 {
		sh 'oc apply -f Orchestration/deployment-openbankdemo-preprod-02.yaml -n=openbankdemo-preprod-02-apps'
       		sh 'oc apply -f Orchestration/service.yaml -n=openbankdemo-preprod-02-apps'
	 }

	     
}
}	
