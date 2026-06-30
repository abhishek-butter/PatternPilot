console.log("popup.js Loaded");

const analysisBtn=document.getElementById("analysisBtn");
if(analysisBtn){
    const title=document.getElementById("title");
    analysisBtn.addEventListener("click",function(){
        console.log("View Analysis button clicked");
        title.textContent="Analysis Coming Soon ..."
        analysisBtn.textContent="Loading..."
        analysisBtn.disabled=true;
    }
    )

}


