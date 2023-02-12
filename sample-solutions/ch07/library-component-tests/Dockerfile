FROM mcr.microsoft.com/dotnet/sdk:7.0
WORKDIR /app

COPY *.csproj *.csproj
RUN dotnet restore
COPY . .

CMD dotnet test