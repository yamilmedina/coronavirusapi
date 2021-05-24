const child = require("child_process");
const fs = require("fs");

const ENCODING_FORMAT = "utf-8"
const latestTag = child.execSync('git describe --tags $(git rev-list --tags --max-count=1)').toString(ENCODING_FORMAT).replace(/.$/s, '')

start = function () {
    let newVersion = fs.readFileSync("chlog.md", ENCODING_FORMAT).split("=")[1]
    console.log("Latest Tag: " + latestTag)
    console.log("New Version Tag: " + newVersion)    

    fs.writeFileSync("chlog-" + newVersion + ".md", `${new Date()}`);
};

start()