"use strict";

var Shortener = function Shortener(inputId, resultInputId, formId) {
    this.input = $("#" + inputId);
    if (!this.input.length) {
        throw new Error("Input is mandatory.");
    }
    this.resultInput = $("#" + resultInputId);
    if (!this.resultInput.length) {
        throw new Error("Result url input is mandatory.");
    }
    var form = document.getElementById(formId);
    if (!form) {
        throw new Error("Form is mandatory.");
    }

    form.addEventListener("submit", this.doShorten.bind(this));
};

Shortener.prototype.doShorten = function doShorten(event) {
    event.preventDefault();

    var request = new XMLHttpRequest();

    request.addEventListener("load", this.handleRequest.bind(this));
    request.open("GET", "shorten?url=" + this.input.val());
    request.send();
};

Shortener.prototype.handleRequest = function handleRequest(event) {
    if (event.target.status === 200) {
        this.resultInput.val("http://cl.ip/" + event.target.responseText);
    } else {
        console.log("error " + event.target.status);
    }

};

