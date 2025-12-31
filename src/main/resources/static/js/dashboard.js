/* ================= Latest Report ================= */
fetch("/api/dashboard/latest")
    .then(res => res.json())
    .then(data => {
        const list = document.getElementById("latestReport");

        data.parameters.forEach(p => {
            const li = document.createElement("li");
            li.innerHTML = `
                <span>${p.name}</span>
                <span class="${p.status === 'ABNORMAL' ? 'status-abnormal' : 'status-normal'}">
                    ${p.value} ${p.unit}
                </span>
            `;
            list.appendChild(li);
        });
    });

/* ================= Trend Chart ================= */
let trendChart;

function loadTrend(parameter) {
    fetch(`/api/trends/${encodeURIComponent(parameter)}`)
        .then(res => res.json())
        .then(data => {

            const labels = data.map(d => d.date.substring(0,10));
            const values = data.map(d => d.value);

            if (trendChart) {
                trendChart.destroy();
            }

            trendChart = new Chart(document.getElementById("trendChart"), {
                type: 'line',
                data: {
                    labels: labels,
                    datasets: [{
                        label: parameter,
                        data: values,
                        borderColor: '#e74c3c',
                        backgroundColor: 'rgba(231,76,60,0.15)',
                        tension: 0.4,
                        fill: true
                    }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: { display: false }
                    }
                }
            });
        });
}

loadTrend("Blood Sugar");

document.getElementById("parameterSelect")
    .addEventListener("change", function () {
        loadTrend(this.value);
    });

/* ================= History ================= */
fetch("/api/history")
    .then(res => res.json())
    .then(reports => {
        const table = document.getElementById("historyTable");

        reports.forEach(r => {
            r.parameters.forEach(p => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${r.uploadDate.substring(0,10)}</td>
                    <td>${p.name}</td>
                    <td>${p.value} ${p.unit}</td>
                    <td class="${p.status === 'ABNORMAL' ? 'status-abnormal' : 'status-normal'}">
                        ${p.status}
                    </td>
                `;
                table.appendChild(row);
            });
        });
    });
