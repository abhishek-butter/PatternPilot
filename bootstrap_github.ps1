Write-Host "Creating labels..."

$labels = @(
    @("good first issue","Good starting tasks","7057ff"),
    @("feature","New feature","0e8a16"),
    @("research","Research task","5319e7"),
    @("documentation","Documentation","0075ca"),
    @("frontend","Frontend","fbca04"),
    @("backend","Backend","d93f0b"),
    @("enhancement","Enhancement","a2eeef"),
    @("priority-high","High Priority","b60205")
)

foreach ($label in $labels) {
    gh label create $label[0] --color $label[2] --description $label[1] 2>$null
}

Write-Host "Creating issues..."

$issues = @(
    @("Setup Chrome Extension (Manifest V3)","Create the initial Chrome Extension skeleton.`n`nTasks:`n- Create manifest.json`n- Load unpacked extension`n- Verify extension loads","good first issue,feature"),
    @("Create Popup UI","Build popup.html, popup.css and popup.js.","frontend"),
    @("Detect LeetCode Problem Page","Detect whether the current page is a LeetCode problem page.","research"),
    @("Detect Accepted Submission","Research how to detect Accepted submissions using DOM/MutationObserver.","research"),
    @("Read Problem Information","Extract title, difficulty, URL and problem ID.","feature"),
    @("Store Submission Locally","Persist solved problems using chrome.storage.local.","backend"),
    @("Build Dashboard","Display solved count, recent problems and statistics.","frontend"),
    @("Design Confidence Score","Research an algorithm for confidence calculation.","research"),
    @("Build Revision Scheduler","Generate daily revision recommendations.","feature"),
    @("Track Solve Time","Track elapsed time from page load until Accepted.","feature"),
    @("Track Wrong Attempts","Count incorrect submissions before Accepted.","feature"),
    @("Pattern Classification","Automatically classify solved problems by DSA topic.","feature"),
    @("Weak Topic Detection","Identify weakest patterns based on confidence.","feature"),
    @("Daily Revision Queue","Generate today's recommended revision list.","feature"),
    @("Confidence Heatmap","Visualize confidence per topic.","frontend"),
    @("AI Feedback Engine","Generate coaching feedback after each solve.","enhancement"),
    @("AI Revision Notes","Generate concise revision summaries.","enhancement"),
    @("Weekly Progress Report","Create weekly analytics for the user.","enhancement"),
    @("User Authentication","Google Sign-In and accounts.","backend"),
    @("Cloud Sync","Synchronize progress across devices.","backend"),
    @("Export Progress","Export data as JSON or CSV.","feature"),
    @("Public Profile","Optional public statistics page.","enhancement"),
    @("Release v1.0.0","Prepare first stable release.","documentation")
)

foreach ($issue in $issues) {
    gh issue create `
        --title $issue[0] `
        --body $issue[1] `
        --label ($issue[2] -split ",")
}

Write-Host ""
Write-Host "Done!"