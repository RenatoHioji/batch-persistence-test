import requests
import time
from concurrent.futures import ThreadPoolExecutor

BASE_URL = "http://localhost:8080"
HEADERS = {"Content-Type": "application/json"}

SINGLE_ENDPOINT = f"{BASE_URL}/v1/batch"
BATCH_ENDPOINT = f"{BASE_URL}/v2/batch"

PAYLOAD = {"data": "data1"}
BATCH_PAYLOAD = [{"data": "data1"} for _ in range(50)]


def send_single():
    return requests.post(SINGLE_ENDPOINT, json=PAYLOAD, headers=HEADERS)


def send_50_single_requests():
    start = time.perf_counter()

    with ThreadPoolExecutor(max_workers=10) as executor:
        futures = [executor.submit(send_single) for _ in range(50)]
        for f in futures:
            f.result()

    end = time.perf_counter()
    print("✔ Sent 50 individual requests")
    print(f"⏱ Time: {end - start:.3f} seconds\n")


def send_batch_request():
    start = time.perf_counter()

    response = requests.post(
        BATCH_ENDPOINT,
        json=BATCH_PAYLOAD,
        headers=HEADERS
    )

    end = time.perf_counter()
    print("✔ Sent 1 batch request (50 items)")
    print("Status:", response.status_code)
    print(f"⏱ Time: {end - start:.3f} seconds\n")


if __name__ == "__main__":
    print("Starting test...\n")

    send_50_single_requests()
    send_batch_request()

    print("Test finished.")
