function myBehavior(){
    console.log("Hello");
    let btn = document.getElementsByClassName("button")[0];
    let player = document.getElementById("player");
	btn.classList.toggle("paused");
	console.log(player.audioTracks.length);
    (player.paused) ? player.play(): player.pause();
};
