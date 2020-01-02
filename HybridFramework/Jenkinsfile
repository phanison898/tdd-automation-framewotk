pipeline{
	agent any
	
	stages{
		stage('Compile'){
			steps{
				withMaven(maven : 'maven-3.6.3'){
					sh 'mvn clean compile'
				}
			}
		}
		
		stage('Testing'){
			steps{
				withMaven(maven : 'maven-3.6.3'){
					sh 'mvn clean test'
				}
			}
		}
	}
}