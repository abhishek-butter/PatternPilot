if(window.location.href.includes("/problems/")){
    console.log("Problem Page Detected")
}
    console.log("Lettcode Problem Page Detected->wakeup service worker")
const title=document.querySelector("a.text-title-large");
//defensive programing for null check
if(title){
    console.log(title.textContent);
}