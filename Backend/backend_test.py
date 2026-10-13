import requests
import random
import sys

# --- CONFIGURATION ---
BASE_URL = "http://localhost:8080"
REGISTER_URL = f"{BASE_URL}/users/register" # Update if your endpoint is /user/register
LOGIN_URL = f"{BASE_URL}/users/login"       # Update if your endpoint is /user/login
TOPICS_URL = f"{BASE_URL}/topics"

# Terminal Colors for output
GREEN = '\033[92m'
RED = '\033[91m'
RESET = '\033[0m'

def print_result(step_name, success, response=None):
    if success:
        print(f"{GREEN}[PASS] {step_name}{RESET}")
    else:
        print(f"{RED}[FAIL] {step_name}{RESET}")
        if response is not None:
            print(f"       Status: {response.status_code}")
            print(f"       Body: {response.text}")
        sys.exit(1)

def run_tests():
    print("Starting PatternPilot Automated Integration Tests...\n")

    # 1. Generate unique user to avoid PostgreSQL email UNIQUE constraint crashes
    unique_id = random.randint(1000, 9999)
    user_data = {
            "username": "TestUser", # <-- Replaced firstName and lastName with username
            "email": f"testuser{unique_id}@patternpilot.com",
            "password": "password123"
        }

    # 2. Register
    reg_res = requests.post(REGISTER_URL, json=user_data)
    print_result("User Registration", reg_res.status_code in [200, 201], reg_res)

    # 3. Login & Extract JWT
    login_res = requests.post(LOGIN_URL, json={"email": user_data["email"], "password": user_data["password"]})
    print_result("User Login", login_res.status_code == 200, login_res)

    # Assuming your backend returns {"token": "eyJhbG..."} - adjust key if needed
    token = login_res.json().get("token")
    if not token:
        print(f"{RED}[FAIL] Could not extract token from login response.{RESET}")
        sys.exit(1)

    headers = {"Authorization": f"Bearer {token}"}

    # 4. Create Topic
    topic_data = {"topicName": f"Arrays {unique_id}", "confidenceScore": 0.0}
    create_topic_res = requests.post(TOPICS_URL, json=topic_data, headers=headers)
    print_result("Create Topic", create_topic_res.status_code == 201, create_topic_res)

    topic_id = create_topic_res.json().get("topicId")

    # 5. Get All Topics
    get_topics_res = requests.get(TOPICS_URL, headers=headers)
    print_result("Get All Topics", get_topics_res.status_code == 200, get_topics_res)

    # 6. Create Problem
    problem_url = f"{TOPICS_URL}/{topic_id}/problems"
    problem_data = {"problemName": "Two Sum", "confidenceScore": 0.0}
    create_problem_res = requests.post(problem_url, json=problem_data, headers=headers)
    print_result("Create Problem", create_problem_res.status_code == 201, create_problem_res)

    problem_id = create_problem_res.json().get("problemId")

    # 7. Get Problem By ID
    get_prob_res = requests.get(f"{problem_url}/{problem_id}", headers=headers)
    print_result("Get Problem by ID", get_prob_res.status_code == 200, get_prob_res)

    # 8. Update Problem
    update_prob_data = {"problemName": "Two Sum (Updated)", "confidenceScore": 100.0}
    update_prob_res = requests.put(f"{problem_url}/{problem_id}", json=update_prob_data, headers=headers)
    print_result("Update Problem", update_prob_res.status_code == 200, update_prob_res)

    print(f"\n{GREEN}All integration tests passed! Core backend modules are completely verified.{RESET}")

if __name__ == "__main__":
    run_tests()