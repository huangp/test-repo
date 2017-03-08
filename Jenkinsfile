node {
    def gitRepo = 'github.com/huangp/test-repo.git'
    def gitBranch = 'demo-pipeline'
    
    stage('checkout') {
        git branch: gitBranch, changelog: false, credentialsId: 'huangp_github', poll: false, url: "https://$gitRepo"
    }

    stage('zanata sync') {
        // from Pipeline Syntax: select 'tool: Use a tool from a predefined Tool Installation' and then generate script
        tool name: 'zanata_cli_4_0_0',
            type: 'org.jenkinsci.plugins.zanata.zanatareposync.ZanataCLIInstall'

        withCredentials(
            [
                [$class: 'UsernamePasswordMultiBinding', credentialsId: 'admin_local_zanata', usernameVariable: 'Z_USERNAME', passwordVariable: 'Z_PASSWORD']
            ]) {
            withEnv(["CLI_HOME=${tool 'zanata_cli_4_0_0'}"]) {
                sh('''
cd file

Z=$CLI_HOME/bin/zanata-cli

$Z -B push --file-types "PLAIN_TEXT[adoc]" --username $Z_USERNAME --key $Z_PASSWORD

$Z -B pull --username $Z_USERNAME --key $Z_PASSWORD

rm -rf .zanata-cache/

git add .
git config user.name "Jenkins"
git config user.email "jenkins-noreply@redhat.com"
git commit -m "$BUILD_ID did this"
  

''')
            }

        }
    }

    stage('push to remote') {
        withCredentials(
            [
                [$class: 'UsernamePasswordMultiBinding', credentialsId: 'huangp_github', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD']
            ]) {

            sh('git push https://${GIT_USERNAME}:${GIT_PASSWORD}@' + gitRepo + ' ' + gitBranch)
        }
    }


}

