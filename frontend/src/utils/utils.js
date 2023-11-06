export function formatDate(date) {
    if(!date) {
        return
    }

    const newDate = new Date(date * 1000);
    const day = formatNumber(newDate.getDate())
    const month = formatNumber(newDate.getMonth() + 1)
    const year = formatNumber(newDate.getFullYear())
    const hour = formatNumber(newDate.getHours())
    const minutes = formatNumber(newDate.getMinutes())
    const seconds = formatNumber(newDate.getSeconds())

    return `${day}/${month}/${year} ${hour}:${minutes}:${seconds}`
}

function formatNumber(number) {
    return number.toString().padStart(2, "0")
}