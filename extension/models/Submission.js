class Submission {

    constructor(sid,verdict,runtime,memory,date,language) {
        this.sid = sid;
        this.verdict = verdict;
        this.runtime = runtime;
        this.memory = memory;
        this.date = date;
        this.language = language;

    }
    getDate(){
        return this.date;
    }
    getLanguage(){
        return this.language;
    }
    getVerdict(){
        return this.verdict;
    }
    getSid(){
        return this.sid;
    }
    getRuntime(){
        return this.runtime;
    }
    getMemory(){
        return this.memory;
    }
    static fromJson(rawSubmission){
        const s=new Submission(
        rawSubmission.sid,
        rawSubmission.verdict,
        rawSubmission.runtime,
        rawSubmission.memory,
        new Date(rawSubmission.date),
        rawSubmission.language,
        );
        return s;

    }
}