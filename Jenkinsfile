node {
    def gitRepo = 'github.com/huangp/test-repo.git'
    def gitBranch = 'trans'

    stage('checkout') {
        git branch: gitBranch, changelog: false, credentialsId: 'huangp_github',
            poll: false, url: "https://$gitRepo"
    }

    stage('zanata sync') {
        step(
            [$class: 'ZanataSyncStep', zanataCredentialsId: 'admin_local_zanata', zanataLocaleIds: '', zanataProjectConfigs: '', zanataURL: ''])
    }

    stage('check missing translation') {
        File workspace = new File(".")
        def poFiles = new FileNameByRegexFinder().
            getFileNames(workspace.absolutePath, /.*\.po/)
        def missingTrans = poFiles.findAll {
            File file = new File(it)
            String line
            boolean poHeader = false
            boolean foundMissingTrans = false
            file.withReader { reader ->
                while ((line = reader.readLine()) != null) {
                    if (line ==~ /msgid ""/) {
                        poHeader = true
                    }
                    if (line ==~ /msgstr[\[\d\]]{0,3} ".+"/) {
                        if (poHeader) {
                            // we ignore empty msgstr in po header
                            poHeader = false
                        } else {
                            // this may provide false alarm when translation starts with a newline
                            foundMissingTrans = true
                            break
                        }
                    }
                }
            }
            foundMissingTrans
        }

        if (missingTrans.size() > 0) {
            println "$missingTrans need translation"
            mail bcc: '', body: "$missingTrans need translation", cc: '',
                from: '', replyTo: '',
                subject: 'insights frontend needs translation',
                to: 'pahuang@redhat.com'
        }
    }


}
