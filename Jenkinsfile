@Library('ecs-fargate-deployment-sharedlibrary_refactor')_

node{
    configFileProvider([configFile(fileId: "deployPipelineRefactor", variable: 'deployPipelineRefactor',targetLocation:"$WORKSPACE/deployPipelineRefactor.groovy")]){
        def rootDir = pwd()
        def tools = load "${rootDir}/deployPipelineRefactor.groovy"
    }
}