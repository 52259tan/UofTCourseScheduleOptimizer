import requests
def getCourseInfoRaw(courseInfo):
    courseName = courseInfo[0:8]
    courseCode = courseInfo[-1]

    url = "https://api.easi.utoronto.ca/ttb/getPageableCourses"

    payload = {
        "courseCodeAndTitleProps": {
            "courseCode": courseName,
            "courseTitle": "",
            "courseSectionCode": courseCode
        },
        "departmentProps": [],
        "campuses": [],
        "sessions": ["20239", "20241", "20239-20241"],
        "requirementProps": [],
        "instructor": "",
        "courseLevels": [],
        "deliveryModes": [],
        "dayPreferences": [],
        "timePreferences": [],
        "divisions": ["ARTSC"],
        "creditWeights": [],
        "page": 1,
        "pageSize": 20,
        "direction": "asc"
    }
    headers = {
        "Content-Type": "application/json",
        "Accept": "application/json, text/plain, */*",
        "Accept-Encoding": "gzip, deflate, br",
        "Accept-Language": "en-US,en;q=0.9,zh-TW;q=0.8,zh-CN;q=0.7,zh;q=0.6",
        "Connection": "keep-alive",
        "Origin": "https://ttb.utoronto.ca",
        "Referer": "https://ttb.utoronto.ca/",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-site",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36",
        "sec-ch-ua": "^\^Google",
        "sec-ch-ua-mobile": "?0"
    }

    response = requests.request("POST", url, json=payload, headers=headers)
    return response.json()

