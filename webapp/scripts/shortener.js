"use strict";

var Shortener = function Shortener(inputId, buttonId) {
    this.input = $("#" + inputId);
    if (!this.input.length) {
        throw new Error("Input is mandatory.");
    }
    var button = document.getElementById(buttonId);
    if (!button) {
        throw new Error("Shorten button is mandatory.");
    }

    button.addEventListener("click", this.doShorten.bind(this));
};

Shortener.prototype.doShorten = function doShorten() {
    var request = new XMLHttpRequest();

    request.addEventListener("load", this.handleRequest.bind(this));
    request.open("GET", "shorten?url=" + this.input.val());
    request.send();
};

Shortener.prototype.handleRequest = function handleRequest(event) {
    if (event.target.status === 200) {
        console.log(event.target.responseText);
    } else {
        console.log("error " + event.target.status);
    }

};

