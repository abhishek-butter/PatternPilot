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
        createSubmission(sid,verdict,runtime,memory,date,language){
                    const submission=new Submission(sid,verdict,runtime,memory,date,language);
                     this.addSubmission(submission);
        }
        addSubmission(submission) {
        let check=false;
        for(let i of this.submissions){
            if(i.getSid()==submission.getSid()){
                check=true;
                break;
            }
        }
        if(!check)this.submissions.push(submission);
        }
        getUrl(){
         return this.url;
        }

        getDifficulty(){
         return this.difficulty;
        }
        static fromJson(json){
            const p=new Problem(json.title,
            json.difficulty,
            json.url,
            )
            for(let i of json.submissions){
                p.addSubmission(Submission.fromJson(i));
            }

            return p;
        }

}