describe("Shortener", function () {
    var shortener;

    beforeEach(function () {
        setFixtures(
            '<input id="input-id" />' +
            '<button type="button" id="button-id">Shorten</button>'
        );
        jasmine.Ajax.install();
        shortener = new Shortener("input-id", "button-id");
    });

    describe("on startup", function () {
        it("requires the input", function () {
            expect(function () {
                new Shortener();
            }).toThrow(new Error("Input is mandatory."));
        });

        it("requires the button", function () {
            expect(function () {
                new Shortener("input-id");
            }).toThrow(new Error("Shorten button is mandatory."));
        });
    });

    it("calls shorten controller on button click", function () {
        $("input").val("http://some.url");
        $("button").click();

        var request = jasmine.Ajax.requests.mostRecent();
        expect(request.url).toBe('shorten?url=http://some.url');
        expect(request.method).toBe('GET');
    });

    afterEach(function () {
        jasmine.Ajax.uninstall();
    });

});
