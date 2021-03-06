"use strict";

var Shortener = function Shortener(inputId, resultInputId, testLinkContainer, formId) {
    this.input = $("#" + inputId);
    if (!this.input.length) {
        throw new Error("Input is mandatory.");
    }
    this.resultInput = $("#" + resultInputId);
    if (!this.resultInput.length) {
        throw new Error("Result url input is mandatory.");
    }
    this.testLinkContainer = $("#" + testLinkContainer);
    if (!this.testLinkContainer.length) {
        throw new Error("Test link container is mandatory.");
    }
    var form = document.getElementById(formId);
    if (!form) {
        throw new Error("Form is mandatory.");
    }

    this.testLink = $('<a href="" id="test-link" target="_blank">Try me!</a>');
    this.testLinkContainer.append(this.testLink);

    form.addEventListener("submit", this.doShorten.bind(this));
};

Shortener.prototype.doShorten = function doShorten(event) {
    event.preventDefault();

    var request = new XMLHttpRequest();

    request.addEventListener("load", this.handleRequest.bind(this));
    request.open("GET", "shorten?url=" + encodeURIComponent(this.input.val()));
    request.send();
};

Shortener.prototype.handleRequest = function handleRequest(event) {
    if (event.target.status === 200) {
        this.updateShortenedUrls(event.target.responseText);
    } else {
        console.log("error " + event.target.status);
    }
};

Shortener.prototype.updateShortenedUrls = function updateShortenedUrls(hashedUrl) {
    this.resultInput.val("http://cl.ip/" + hashedUrl);
    this.testLink.attr("href", "/" + hashedUrl);
    this.testLinkContainer.removeAttr("hidden");
};