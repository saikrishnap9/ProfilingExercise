def collectionFile ='C:\Users\Administrator\Desktop\ProfilingExercise\ProfilingExercise.postman_collection.json'
def environment =''

def command= "newman run ${collectionFile} --environment ${environmentFile}"

def process=command.execute()

def output = process.text
def error = process.err.text

if (process.existValue() == 0){
	println "Newman run completed successfully!"
	println output
} else {
	println "Newman run failed"
	println error
}