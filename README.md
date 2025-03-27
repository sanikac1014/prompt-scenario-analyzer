# 🚀 Prompt Scenario Analyzer

A full-stack web application that uses AI to analyze user-defined scenarios and constraints, returning structured insights. Built with **React** (frontend) and **Spring Boot** (backend).

---

## 🧩 Features
- ✅ Input a scenario and constraints
- ⚠️ Displays potential pitfalls
- ✅ Suggests strategies and resources
- 🧠 Works with OpenAI (or mock mode)
- ✅ Backend validation + tests included

---

## 🗂️ Project Structure
```
prompt-engineer-project/
├── frontend/        # React app (UI)
└── backend/         # Spring Boot app (API)
└── screenshots/         
```

---

## ⚙️ Backend Setup (Spring Boot)

### 🔧 Requirements:
- Java 17 or higher (Java 24 supported)
- Maven installed

### 🔌 To Run:
```bash
cd backend
./mvnw spring-boot:run
```
> API will run at: `http://localhost:8080/api/analyze-scenario`

---

## 💻 Frontend Setup (React)

### 🔧 Requirements:
- Node.js and npm

### ▶️ To Run:
```bash
cd frontend
npm install
npm start
```
> App runs at: `http://localhost:3000`
> Connects to: `http://localhost:8080/api/analyze-scenario`

---

## 🌐 API Endpoint

### POST `/api/analyze-scenario`
**Request:**
```json
{
  "scenario": "Our startup has 5 weeks to build a new product...",
  "constraints": ["Budget: $7,500", "Team: 2 devs"]
}
```
**Response:**
```json
{
  "scenarioSummary": "Summary text",
  "potentialPitfalls": ["Risk 1"],
  "proposedStrategies": ["Strategy 1"],
  "recommendedResources": ["Tool 1"],
  "disclaimer": "This is AI-generated..."
}
```

---

## 🧪 Testing (Backend)

Run tests with:
```bash
./mvnw test
```
> Includes both happy path and validation edge cases

---

## 🧠 AI Configuration

The app is currently using **mock mode**.

To switch to real OpenAI API:
- Replace `generateAnalysis()` in `AiService.java` with the OpenAI API call
- Add your OpenAI key in `application.properties`

---

## 📸 Screenshots 
- ✅ Full UI with input & response
- ✅ DevTools > Network tab showing request & response

---
