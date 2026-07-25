if(window.location.href.includes("/problems/")){
    console.log("Problem Page Detected")
}
    console.log("Lettcode Problem Page Detected->wakeup service worker")
const title=document.querySelector("div.text-title-large");
console.log(title.innerText);

//defensive programing for null check
if(title){
    console.log(title.textContent);
}