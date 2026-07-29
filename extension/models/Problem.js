class Problem{



        constructor(title,difficulty,url) {
            this.title = title;
            this.difficulty = difficulty;
            this.url = url;
            this.submissions=[]
        }
        getTitle(){

            return this.title;
        }
        setTitle(title) {
            this.title = title;
        }
        createSubmission(date,language,verdict){
                    const submission=new Submission(date,language,verdict);
                    return submission;


        }
        addSubmission(submission) {
        this.submissions.push(submission);

        }
        getUrl(){
        return this.url;


        }
        getDifficulty(){
        return this.difficulty;
        }

}