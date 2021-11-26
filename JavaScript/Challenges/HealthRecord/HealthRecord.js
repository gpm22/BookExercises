function serialize(patientDataArray) {
    const result = [""];
  
    patientDataArray.sort(compare);
  
    patientDataArray.forEach((patientData) => {
      const paid = patientData.paid ? "Y" : "N";
      let codes = "";
  
      if(patientData.treatmentCodes != undefined){
          codes = patientData.treatmentCodes.join(",");
      }
  
      if (result[result.length - 1].includes(patientData.patientId)) {
        result[
          result.length - 1
        ] += `\n+${patientData.visitDate}|${paid}|${codes}`;
      } else {
        result.push(
          `>${patientData.patientId}\n+${patientData.visitDate}|${paid}|${codes}`
        );
      }
    });
  
    result.shift();
  
    return result.join("\n");;
  }
  
  function compare(a, b) {
    if (a.patientId < b.patientId) {
      return -1;
    }
    if (a.patientId > b.patientId) {
      return 1;
    }
  
    if (a.visitDate < b.visitDate) {
      return -1;
    }
    if (a.visitDate > b.visitDate) {
      return 1;
    }
  
    return 0;
  }
  
  function deserialize(patientStringEnter) {
    
    const patientStringArray = patientStringEnter.split(">").map((i) => i.trim());
    patientStringArray.shift();
    const result = [];
  
    patientStringArray.forEach((patientString) => {
  
      const lines = patientString.split("\n");
  
      for(let i = 1; i < lines.length; i++){
  
          const segments = lines[i].split("|");
        
  
          let treatmentCodesValue = segments[2].split(",").map(Number);
  
          treatmentCodesValue = (treatmentCodesValue==0 ? [] : treatmentCodesValue);
          result.push({
              patientId: lines[0],
              visitDate: segments[0].substr(1),
              paid: segments[1] === "Y",
              treatmentCodes: treatmentCodesValue,
            });
  
      }
    });
  
    return result;
  }