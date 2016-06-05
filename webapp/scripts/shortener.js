"use strict";

var Shortener = function Shortener(inputId, resultInputId, buttonId) {
    this.input = $("#" + inputId);
    if (!this.input.length) {
        throw new Error("Input is mandatory.");
    }
    this.resultInput = $("#" + resultInputId);
    if (!this.resultInput.length) {
        throw new Error("Result url input is mandatory.");
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
        this.resultInput.val(event.target.responseText);
    } else {
        console.log("error " + event.target.status);
    }

};

