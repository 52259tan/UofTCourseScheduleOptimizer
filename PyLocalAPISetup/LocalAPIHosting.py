from fastapi import FastAPI
import UofTCourseScraping
app = FastAPI()

@app.get('/{courseInfo}')
async def read_item(courseInfo):
    return UofTCourseScraping.getCourseInfoRaw(courseInfo)
