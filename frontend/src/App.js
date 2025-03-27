import React, { useState } from "react";
import "./index.css";

function App() {
  const [scenario, setScenario] = useState("");
  const [constraints, setConstraints] = useState([""]);
  const [response, setResponse] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");
  const [scenarioError, setScenarioError] = useState("");

  const handleConstraintChange = (index, value) => {
    const updatedConstraints = [...constraints];
    updatedConstraints[index] = value;
    setConstraints(updatedConstraints);
  };

  const addConstraintField = () => {
    setConstraints([...constraints, ""]);
  };

  const removeConstraintField = (index) => {
    const updatedConstraints = constraints.filter((_, i) => i !== index);
    setConstraints(updatedConstraints);
  };

  const handleSubmit = async () => {
    setScenarioError("");

    const wordCount = scenario.trim().split(/\s+/).length;
    if (wordCount < 20 || wordCount > 300) {
      setScenarioError("Scenario must be between 20 and 300 words.");
      return;
    }

    setIsLoading(true);
    setError("");

    try {
      const res = await fetch("http://localhost:8080/api/analyze-scenario", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          scenario,
          constraints: constraints.filter((c) => c.trim() !== ""),
        }),
      });

      if (!res.ok) throw new Error("Failed to analyze the scenario.");
      const data = await res.json();
      setResponse(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-r from-white to-purple-100 flex items-center justify-center p-6">
      <div className="bg-white rounded-2xl shadow-lg p-8 max-w-3xl w-full">
        <h1 className="text-3xl font-bold text-center mb-6 text-transparent bg-clip-text bg-gradient-to-r from-pink-500 to-purple-600 flex items-center justify-center">
          <span role="img" aria-label="rocket" className="mr-2">🚀</span> Prompt Scenario Analyzer
        </h1>

        <div className="mb-4">
          <label className="block font-semibold text-gray-700 mb-2">Scenario</label>
          <textarea
            value={scenario}
            onChange={(e) => setScenario(e.target.value)}
            className="w-full border border-gray-300 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"
            rows={5}
            placeholder="Describe your scenario here..."
          />
          {scenarioError && (
            <p className="text-red-500 text-sm mt-1">{scenarioError}</p>
          )}
        </div>

        <div className="mb-4">
          <label className="block font-semibold text-gray-700 mb-2">Constraints</label>
          {constraints.map((constraint, index) => (
            <div key={index} className="flex items-center mb-2">
              <input
                value={constraint}
                onChange={(e) => handleConstraintChange(index, e.target.value)}
                className="flex-1 border border-gray-300 rounded-lg p-2 mr-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
                placeholder={`Constraint ${index + 1}`}
              />
              <button
                onClick={() => removeConstraintField(index)}
                className="text-pink-500 hover:text-pink-700 text-lg"
              >
                ❌
              </button>
            </div>
          ))}
          <button
            onClick={addConstraintField}
            className="text-sm text-blue-600 hover:underline mt-1"
          >
            + Add another constraint
          </button>
        </div>

        <button
          onClick={handleSubmit}
          disabled={isLoading}
          className="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-lg w-full transition duration-200"
        >
          {isLoading ? "Analyzing..." : "Submit"}
        </button>

        {error && (
          <p className="text-red-500 mt-4 text-center">{error}</p>
        )}

        {response && (
          <div className="mt-8 space-y-6">
            <div>
              <h2 className="text-xl font-semibold flex items-center mb-1">
                📝 Scenario Summary
              </h2>
              <p>{response.scenarioSummary}</p>
            </div>

            <div>
              <h2 className="text-xl font-semibold text-yellow-600 flex items-center mb-1">
                ⚠️ Potential Pitfalls
              </h2>
              <ul className="list-disc list-inside space-y-1">
                {response.potentialPitfalls?.map((item, i) => (
                  <li key={i}>{item}</li>
                ))}
              </ul>
            </div>

            <div>
              <h2 className="text-xl font-semibold text-green-700 flex items-center mb-1">
                ✅ Proposed Strategies
              </h2>
              <ul className="list-disc list-inside space-y-1">
                {response.proposedStrategies?.map((item, i) => (
                  <li key={i}>{item}</li>
                ))}
              </ul>
            </div>

            <div>
              <h2 className="text-xl font-semibold text-blue-800 flex items-center mb-1">
                🛠️ Recommended Resources
              </h2>
              <ul className="list-disc list-inside space-y-1">
                {response.recommendedResources?.map((item, i) => (
                  <li key={i}>{item}</li>
                ))}
              </ul>
            </div>

            <p className="text-sm text-gray-500 italic mt-4">
              🔖 {response.disclaimer}
            </p>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
