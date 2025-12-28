# R99 / 8AEC Smart Ring – Python + Android Tools

This repository contains **reverse-engineered tools** for interacting with an **R99 / 8AEC Smart Ring** (the type commonly used with the *SmartHealth* / *RWFit* style apps).

It includes:
- 🐍 **Python code** for connecting to the ring over BLE, sending commands, and decoding raw health data
- 📱 **An Android app** (Kotlin / Jetpack Compose) used for BLE experimentation, debugging, and eventual Health Connect integration

> ⚠️ **Disclaimer**
>
> This project is **unofficial**, **unsupported**, and **not affiliated** with the device manufacturer or the SmartHealth app.  
> Everything here is based on reverse-engineering BLE traffic and decompiled app logic.

---

## Python Side

The Python code focuses on **low-level BLE protocol handling**, closely mirroring how the official app communicates with the ring.

### Key Features

- BLE connection using **Bleak**
- MTU-aware packet chunking & reassembly
- CRC16 validation (YCBT variant)
- Command queue with ACK tracking
- Health session reconstruction
- Faithful ports of the original Java unpacking logic

### Supported Data (So Far)

- Sleep sessions
- Historical health records (steps, HR, SpO₂, respiration, HRV, CVRR, temperature)
- Device capability flags

Capability decoding is handled via a direct bit-mapping table extracted from the app logic.

The main BLE client and health parsing logic live in `ring4.py`.

---

### Python Requirements

- Python **3.10+**
- `bleak`

Install dependencies:

```bash
pip install bleak
```

---

### Example Usage

```python
import asyncio
from ring4 import SmartHealthR99

async def main():
    ring = SmartHealthR99("AA:BB:CC:DD:EE:FF", mtu=185)
    await ring.connect()

    # Request health history
    await ring.send_cmd(1284)
    await ring.send_cmd(1289)

    await asyncio.sleep(30)
    await ring.disconnect()

asyncio.run(main())
```

---

## Android App

The Android project exists primarily as a **development + validation tool**.

### Current Goals

- BLE connection testing on real Android hardware
- Live inspection of notifications and frames
- Health Connect integration (in progress)
- UI for triggering commands and viewing parsed output

### Tech Stack

- Kotlin
- Jetpack Compose
- Android BLE APIs
- Health Connect (WIP)

This app is intentionally simple and debug-oriented.

---

## Protocol Notes

- Uses custom **BE94** service UUIDs
- Commands are `(group << 8) | subtype`
- Responses may arrive fragmented and out-of-order
- Health data is streamed in multi-packet sessions
- Timestamps are seconds since **2000-01-01**, converted to local time

Where possible, parsing logic is a **1:1 port** of the original Java code to ensure correctness.

---

## Why This Exists

- Personal data ownership
- Learning BLE protocol reverse-engineering
- Avoiding vendor lock-in
- Experimenting with Health Connect and custom health pipelines

This is a **research / hobby project**, not a polished SDK.

---

## Status

🚧 **Work in progress**

Things change as firmware versions change.

Expect:
- Partial implementations
- Missing commands
- Occasional breakage after ring updates

---

## License

MIT

Use at your own risk.
