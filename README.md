# Coaching KPI

This repository contains the scripts and data files needed for daily pipeline job. Follow the instructions below to download the artifacts or run the scripts to generate the latest CSV and XLSX files.

## Downloading Artifacts

To download the artifacts:
1. Navigate to **Build -> Artifacts** in the GitLab repository.
2. Find the daily pipeline job.
3. Download the `artifacts.zip` file.

It contains the following files:
- `vector_bugs_with_filter2.csv`
- `summarized.csv`
- `formated_summarized.xlsx`

## Cloning the Repository and Running the Script

Alternatively, you can clone the repository and run the provided Python script to generate the latest CSV and XLSX files.

### Steps:

1. Clone the repository


2. Install the required Python packages:
    ```bash
    pip install prompt_toolkit
    pip install jira
    pip install datetime
    pip install holidays
    pip install pandas
    pip install openpyxl
    ```

3. Run the script to generate the newest CSV and XLSX files:
    ```bash
    python3 bmwjira2.py -t .token
    ```
    