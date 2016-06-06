describe("Shortener", function () {
    var shortener;

    beforeEach(function () {
        setFixtures(
            '<form id="some-form">' +
            '   <input id="url-to-shorten-input" />' +
            '   <button type="submit" id="shorten-button">Shorten</button>' +
            '   <input id="hashed-url-input" />' +
            '   <div id="test-link-container"></div>' +
            '</form>'
        );
        jasmine.Ajax.install();
        shortener = new Shortener("url-to-shorten-input", "hashed-url-input", "test-link-container", "some-form");
    });

    describe("on startup", function () {
        it("requires the input to enter url", function () {
            expect(function () {
                new Shortener();
            }).toThrow(new Error("Input is mandatory."));
        });

        it("requires the input to display result", function () {
            expect(function () {
                new Shortener("url-to-shorten-input");
            }).toThrow(new Error("Result url input is mandatory."));
        });

        it("a container to display the test link", function () {
            expect(function () {
                new Shortener("url-to-shorten-input", "hashed-url-input");
            }).toThrow(new Error("Test link container is mandatory."));
        });

        it("requires the form", function () {
            expect(function () {
                new Shortener("url-to-shorten-input", "hashed-url-input", "test-link-container");
            }).toThrow(new Error("Form is mandatory."));
        });
    });

    it("calls shorten controller on button click", function () {
        $("input#url-to-shorten-input").val("http://some.url");
        $("button").click();

        var request = jasmine.Ajax.requests.mostRecent();

        expect(request.url).toBe('shorten?url=http://some.url');
        expect(request.method).toBe('GET');
    });

    it("shows the resulting url in another input", function () {
        $("input#url-to-shorten-input").val("http://some.url");
        $("button").click();

        jasmine.Ajax.requests.mostRecent().respondWith({
            status: 200,
            responseText: "hashed"
        });

        var resultInput = $("#hashed-url-input");
        expect(resultInput).toHaveValue("http://cl.ip/hashed");
    });

    it("also shows the resulting url as a clickable link", function () {
        $("input#url-to-shorten-input").val("http://some.url");
        $("button").click();

        jasmine.Ajax.requests.mostRecent().respondWith({
            status: 200,
            responseText: "hashed"
        });

        var testLink = $("#test-link-container a");
        expect(testLink).toHaveAttr("href", "/hashed");
        expect(testLink).toHaveAttr("target", "_blank");
        expect(testLink).toHaveHtml("http://cl.ip/hashed");
    });

    afterEach(function () {
        jasmine.Ajax.uninstall();
    });

});
